package xiaoqiang.wang.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import xiaoqiang.wang.modeldomain.*;
import xiaoqiang.wang.service.*;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class MainController {
    @Autowired
    private IUserInfoService iuserInfoService;
    @Autowired
    private IBookCategoryInfoService ibookCategoryInfoService;
    @Autowired
    private IBookInfoService ibookInfoService;
    @Autowired
    private IBookSellService iBookSellService;
    @Autowired
    private IBookBuyService iBookBuyService;
    @Autowired
    private IOrderInfoService iOrderInfoService;
    @Autowired
    private IOrderDetailService iOrderDetailService;

    private final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/allusers")
    public MyResponseBody allusers()
    {
        MyResponseBody ret = new MyResponseBody(true, "all data for debugging", iuserInfoService.findAll());
        return ret;
    }

    @RequestMapping(value = "/register")
    public MyResponseBody register(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "avatarBase64") String avatarBase64
    )
    {
        // TO-DO login expiration check-out
        logger.info(avatarBase64);
        MyResponseBody ret = null;
        // userInfoService.deleteByUserName(userName);
        if(iuserInfoService.findByUserName(userName) != null) {
            ret = new MyResponseBody(false, "userName has already existed", null);
        } else if(iuserInfoService.findByEmail(email) != null) {
            ret = new MyResponseBody(false, "email has already existed", null);
        } else {
            String avatarURL = uploadImage(avatarBase64);
            if(avatarURL != null) {
                iuserInfoService.insertOne(userName, password, email, avatarURL);
                ret = new MyResponseBody(true, "", null);
            } else {
                ret = new MyResponseBody(false, "avatar uploading failed", null);
            }
        }

        return ret;
    }

    @RequestMapping(value = "/login")
    public MyResponseBody login(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password
    )
    {
        // TO-DO login expiration check-out
        MyResponseBody ret = null;
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName doesn\'t exist", null);
        } else if(!userInfo.getPassword().equals(password)) {
            ret = new MyResponseBody(false, "password is not matched", null);
        } else {
            ret = new MyResponseBody(true, "", new LoginResponseBody(userInfo));
        }
        return ret;
    }

    private String uploadImage(String imageBase64String)
    {
        //byte[] imageByteArray = Base64.getUrlDecoder().decode(imageBase64String);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] imageByteArray = null;
        try {
            imageByteArray = decoder.decodeBuffer(imageBase64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ret = null;
        if(imageByteArray != null) {
            // machine room
            // East-PRC
            Configuration configuration = new Configuration(Zone.zone0());
            UploadManager uploadManager = new UploadManager(configuration);
            String akID = "3Ew5LFXsfvqFuk4YimTh4k3Je0kk4QiOdB32ygQ9";
            String sk = "Z7Cnjg0qyqpMfAgVP4qsEsCzels28f3A6l_W0v6L";
            Auth authentication = Auth.create(akID, sk);
            // bucket name
            String bucket = "old-book-system-bucket";
            String upToken = authentication.uploadToken(bucket);
            // with or without key
            try {
                Response response = uploadManager.put(imageByteArray, null, upToken);
                DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                ret = defaultPutRet.key;
            } catch (QiniuException e) {
                e.printStackTrace();
            }
        }
        return ret == null ? null : "http://psz7tw3xz.bkt.clouddn.com/" + ret;
    }


    @RequestMapping(value = "/allcategories")
    public MyResponseBody allcategories()
    {
        List<BookCategoryInfoResponseBody> ret = ibookCategoryInfoService.findAll().stream()
                .map(BookCategoryInfoResponseBody::new)
                .collect(Collectors.toList());
        return new MyResponseBody(true, "", ret);
    }

    private BookInfo parserBookInfoAndCategoryInfos(
            String bookName,
            String bookIntro,
            String bookIntroURL,
            String bookImageBase64,
            List<Long> bookCategoryKeys
    )
    {
        String bookImageURL = uploadImage(bookImageBase64);
        List<BookCategoryInfo> bookCategoryInfos = bookCategoryKeys.stream()
                .map(Long::valueOf)
                .map(pk -> ibookCategoryInfoService.findById(pk))
                .collect(Collectors.toList());

        // new a BookInfo
        BookInfo bookInfo = ibookInfoService.insertOne(bookName, bookIntro, bookIntroURL, bookImageURL, bookCategoryInfos);

        // book & category relate
        for(BookCategoryInfo b : bookCategoryInfos) {
            b.addBookInfo(bookInfo);
        }

        return bookInfo;
    }

    @RequestMapping(value = "/uploadbooksell")
    public MyResponseBody uploadBookSell(
            @RequestParam String userName,
            @RequestParam String bookName,
            @RequestParam String bookIntro,
            @RequestParam String bookIntroURL,
            @RequestParam String bookImageBase64,
            @RequestParam List<Long> bookCategoryKeys,
            @RequestParam double originPrice,
            @RequestParam double sellPrice
    )
    {
        // TO-DO login expiration check-out
        MyResponseBody ret = null;

        // get the user
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret =  new MyResponseBody(false, "userName doesn\'t exist", null);
        } else {
            // parse a book with category
            BookInfo bookInfo = parserBookInfoAndCategoryInfos(bookName, bookIntro, bookIntroURL, bookImageBase64, bookCategoryKeys);

            // new a bookSell
            BookSell bookSell = iBookSellService.insertOne(bookInfo, userInfo, originPrice, sellPrice);

            // book & bookSell relate
            bookInfo.addBookSell(bookSell);

            // user & bookSell relate
            userInfo.addBookSell(bookSell);

            ret =  new MyResponseBody(true, "", null);
        }

        return ret;
    }

    @RequestMapping(value = "/querybooksell")
    public MyResponseBody queryBookSell(
            @RequestParam String bookName,
            @RequestParam String sellerName,
            @RequestParam List<Long> bookCategoryKeys
    )
    {
        MyResponseBody ret = null;
        List<BookSell> bookSells = null;
        if(bookName.isEmpty() && sellerName.isEmpty() && bookCategoryKeys.isEmpty()) {
            bookSells = iBookSellService.findAll().stream()
                    .filter(bs -> bs.getOrderDetail() == null)
                    .collect(Collectors.toList());
        } else {
            List<BookCategoryInfo> bookCategoryInfos = bookCategoryKeys.stream()
                    .map(l -> ibookCategoryInfoService.findById(l))
                    .collect(Collectors.toList());

            bookSells = iBookSellService.findAllByBookCategoryInfos(bookCategoryInfos);

            if(!bookName.isEmpty()) {
                bookSells = bookSells.stream()
                        .filter(bs -> bs.getBookInfo().getBookName().contains(bookName))
                        .collect(Collectors.toList());
            }

            if(!sellerName.isEmpty()) {
                bookSells = bookSells.stream()
                        .filter(bs -> bs.getUserInfo().getUserName().contains(sellerName))
                        .collect(Collectors.toList());
            }

        }
        List<BookSellResponseBody> bookSellResponseBodies = bookSells.stream()
                .map(BookSellResponseBody::new)
                .collect(Collectors.toList());
        return new MyResponseBody(true, "", bookSellResponseBodies);
    }

    @RequestMapping(value = "/uploadbookbuy")
    public MyResponseBody uploadBookBuy(
            @RequestParam String userName,
            @RequestParam String bookName,
            @RequestParam String bookIntro,
            @RequestParam String bookIntroURL,
            @RequestParam String bookImageBase64,
            @RequestParam List<Long> bookCategoryKeys,
            @RequestParam double lowerPrice,
            @RequestParam double upperPrice
    )
    {
        // TO-DO login expiration check-out
        MyResponseBody ret = null;

        // get the user
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret =  new MyResponseBody(false, "userName doesn\'t exist", null);
        } else {
            BookInfo bookInfo = parserBookInfoAndCategoryInfos(bookName, bookIntro, bookIntroURL, bookImageBase64, bookCategoryKeys);

            // new a book buy
            BookBuy bookBuy = iBookBuyService.insertOne(bookInfo, userInfo, lowerPrice, upperPrice);

            // book & book buy relate
            bookInfo.addBookBuy(bookBuy);

            // user & book buy relate
            userInfo.addBookBuy(bookBuy);

            ret = new MyResponseBody(true, "", null);
        }

        return ret;
    }

    @RequestMapping(value = "/uploadorderdetail")
    public MyResponseBody uploadOrderDetail(
            @RequestParam String userName,
            @RequestParam Long bookSellKey,
            @RequestParam String tradePlace,
            @RequestParam(required = false) Date tradeTimestamp
            // not required & may be null
            )
    {
        // TO-DO login expiration check-out
        MyResponseBody ret = null;
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        BookSell bookSell = iBookSellService.findOne(bookSellKey);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName doesn\'t exist", null);
        } else {
            // get the current order info
            OrderInfo shoppingCart = iOrderInfoService.findShoppingCart(userInfo);

            // new an order detail
            OrderDetail orderDetail = iOrderDetailService.insertOne(shoppingCart, bookSell, tradePlace, tradeTimestamp);

            // order info & order detail relate
            shoppingCart.addOrderDetail(orderDetail);

            // book sell & order detail relate
            bookSell.setOrderDetail(orderDetail);

            ret = new MyResponseBody(true, "", null);
        }
        return ret;
    }

    @RequestMapping(value = "/deleteorderdetail")
    public MyResponseBody deleteOrderDetail(
            @RequestParam String userName,
            @RequestParam Long orderDetailKey
    )
    {
        // TO-DO login expiration check-out
        MyResponseBody ret = null;
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "", null);
        } else {
            OrderDetail orderDetail = iOrderDetailService.findOne(orderDetailKey);
            if(orderDetail != null) {
                orderDetail = null;
                userInfo = null;
                iOrderDetailService.deleteOne(orderDetailKey);

                userInfo = iuserInfoService.findByUserName(userName);
                OrderInfo shoppingCart = iOrderInfoService.findShoppingCart(userInfo);
                if(shoppingCart.getOrderDetails().isEmpty()) {
                    Long l = new Long(shoppingCart.getId());
                    userInfo = null;
                    shoppingCart = null;
                    iOrderInfoService.deleteOne(l);
                }

                ret = new MyResponseBody(true, "", iOrderInfoService.findAll());
            } else {
                ret = new MyResponseBody(false, "orderDetail key doesn\'t exist", null);
            }

        }

        return ret;
    }

    private boolean setOrderInfoOrderState(String userName, short orderState)
    {
        boolean ret = true;
        // TO-DO login expiration check-out
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = false;
        } else {
            OrderInfo shoppingCart = iOrderInfoService.findShoppingCart(userInfo);
            shoppingCart.setOrderState(orderState);
            ret = true;
        }
        return ret;
    }

    @RequestMapping(value = "/confirmorderinfo")
    public MyResponseBody confirmOrderInfo(
            @RequestParam String userName
    )
    {
        // 2 finish
        if(setOrderInfoOrderState(userName, (short)2)) {
            UserInfo userInfo = iuserInfoService.findByUserName(userName);
            OrderInfo shoppingCart = iOrderInfoService.findShoppingCart(userInfo);
            return new MyResponseBody(true, "", null);
        } else {
            return new MyResponseBody(false, "userName doesn\'t exist", null);
        }
    }

    @RequestMapping(value = "/cancelorderinfo")
    public MyResponseBody cancelOrderInfo(
            @RequestParam String userName
    )
    {
        // 1 cancel
        if(setOrderInfoOrderState(userName, (short)1)) {
            UserInfo userInfo = iuserInfoService.findByUserName(userName);
            OrderInfo shoppingCart = iOrderInfoService.findShoppingCart(userInfo);
            Long l = new Long(shoppingCart.getId());
            userInfo = null;
            shoppingCart = null;
            iOrderInfoService.deleteOne(l);
            return new MyResponseBody(true, "", null);
        } else {
            return new MyResponseBody(false, "userName does\'t exist", null);
        }
    }

    @RequestMapping(value = "/shoppingcart")
    public MyResponseBody shoppingCart(
            @RequestParam String userName
    )
    {
        MyResponseBody ret = null;
        // TO-DO login expiration check-out
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName does\'t exist", null);
        } else {
            OrderInfo shoppingCart = iOrderInfoService.findShoppingCart(userInfo);
            OrderInfoResponseBody orderInfoResponseBody = new OrderInfoResponseBody(shoppingCart);

            ret = new MyResponseBody(true, "", orderInfoResponseBody);
        }
        return ret;
    }

    /**
     * @apiNote
     * all == state 1 || state 2
     * ! state 0
     * @param userName
     * @return
     */
    @RequestMapping(value = "/userorderinfos")
    public MyResponseBody userOrderInfos(
            @RequestParam String userName
    )
    {
        MyResponseBody ret = null;
        // TO-DO login expiration check-out
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName does\'t exist", null);
        } else {
            List<OrderInfoResponseBody> orderInfosResponseBody = userInfo.getOrderInfos().stream()
                    .map(OrderInfoResponseBody::new)
                    .collect(Collectors.toList());
            ret = new MyResponseBody(true, "", orderInfosResponseBody);
        }
        return ret;
    }

    @RequestMapping(value = "/deleteorderinfo")
    public MyResponseBody deleteOrderInfo(
            @RequestParam Long orderInfoKey
    )
    {
        MyResponseBody ret = null;
        // TO-DO login expiration check-out
        OrderInfo orderInfo = iOrderInfoService.findOne(orderInfoKey);
        if(orderInfo == null) {
            ret = new MyResponseBody(false, "orderInfo does\'t exist", null);
        } else {
            orderInfo = null;
            iOrderInfoService.deleteOne(orderInfoKey);
            ret = new MyResponseBody(true, "", null);
        }
        return ret;
    }

    private List<Long> getUserOrderInfosKey(UserInfo userInfo)
    {
        return  userInfo.getOrderInfos().stream()
                    .filter(oi -> oi.getOrderState() != (short)0)
                    .map(OrderInfo::getId)
                    .map(Long::new)
                    .collect(Collectors.toList());
    }

    @RequestMapping(value = "/deleteuserorderinfos")
    public MyResponseBody deleteUserOrderInfos(
            @RequestParam String userName
    )
    {
        MyResponseBody ret = null;
        // TO-DO login expiration check-out
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName does\'t exist", null);
        } else {
            List<Long> ls = getUserOrderInfosKey(userInfo);
            userInfo = null;
            for(Long l : ls) {
                iOrderInfoService.deleteOne(l);
            }
            ret = new MyResponseBody(true, "", null);
        }
        return ret;
    }

    @RequestMapping(value = "/userbooksells")
    public MyResponseBody userBookSells(
            @RequestParam String userName
    )
    {
        MyResponseBody ret = null;
        // TO-DO login expiration check-out
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName does\'t exist", null);
        } else {
            List<BookSellResponseBody> bookSellResponseBodies = userInfo.getBookSells().stream()
                    .map(BookSellResponseBody::new)
                    .collect(Collectors.toList());
            ret = new MyResponseBody(true, "", bookSellResponseBodies);
        }
        return ret;
    }

    @RequestMapping(value = "/deleteuserbooksells")
    public MyResponseBody deleteUserBookSells(
            @RequestParam String userName
    )
    {
        MyResponseBody ret = null;
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName does\'t exist", null);
        } else {
            userInfo.getBookSells().stream()
                    .map(BookSell::getId)
                    .collect(Collectors.toList())
                    .forEach(l -> iBookSellService.deleteOne(l));
            ret = new MyResponseBody(true, "", null);
        }

        return ret;
    }

    @RequestMapping(value = "/deletebooksell")
    public MyResponseBody deleteBookSell(
            @RequestParam Long bookSellKey
    )
    {
        MyResponseBody ret = null;
        BookSell bookSell = iBookSellService.findOne(bookSellKey);
        if(bookSell == null) {
            ret = new MyResponseBody(false, "invalid key", null);
        } else {
            iBookSellService.deleteOne(bookSellKey);
            ret = new MyResponseBody(true, "", null);
        }

        return ret;
    }

    @RequestMapping(value = "/userbookbuys")
    public MyResponseBody userBookBuys(
            @RequestParam String userName
    )
    {
        MyResponseBody ret = null;
        // TO-DO login expiration check-out
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName does\'t exist", null);
        } else {
            List<BookBuyResponseBody> bookBuyResponseBodies = userInfo.getBookBuys().stream()
                    .map(BookBuyResponseBody::new)
                    .collect(Collectors.toList());
            ret = new MyResponseBody(true, "", bookBuyResponseBodies);
        }
        return ret;
    }

    @RequestMapping(value = "/deleteuserbookbuys")
    public MyResponseBody deleteUserBookBuys(
            @RequestParam String userName
    )
    {
        MyResponseBody ret = null;
        UserInfo userInfo = iuserInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName does\'t exist", null);
        } else {
            userInfo.getBookBuys().stream()
                    .map(BookBuy::getId)
                    .collect(Collectors.toList())
                    .forEach(l -> iBookBuyService.deleteOne(l));
            ret = new MyResponseBody(true, "", null);
        }

        return ret;
    }

    @RequestMapping(value = "/deletebookbuy")
    public MyResponseBody deleteBookBuy(
            @RequestParam Long bookBuyKey
    )
    {
        MyResponseBody ret = null;
        BookBuy bookBuy = iBookBuyService.findOne(bookBuyKey);
        if(bookBuy == null) {
            ret = new MyResponseBody(false, "invalid key", null);
        } else {
            iBookBuyService.deleteOne(bookBuyKey);
            ret = new MyResponseBody(true, "", null);
        }

        return ret;
    }

}

