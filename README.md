# old-book-web-back-end

> I have got a full mark in BS curriculum.
>
> Now, I make it public.

## prerequisites

- `JDK1.8`
- `SpringBoot & SpringMVC & Spring Data JPA(Hibernate)`
- `WebSocket, SockJS, STOMP`
- `MySQL`

## build

- `mvn clean package -DskipTests`

## deploy

- `nohup java -jar oldbooksystem-0.0.1.jar >temp.log &`
- or specify port  in terminal `nohup java -jar oldbooksystem-0.0.1.jar --server.port = <PORT> >temp.log &`
- or configure it in `JVM`
- or configure it in `application.peroperties`

## RESTful + Coupling & Incoherent API

```
	websocket: wsHost + '/api/websocket',

    register: httpHost + '/api/register',
    login: httpHost + '/api/login',
    allcategories: httpHost + '/api/allcategories',
    uploadbooksell: httpHost + '/api/uploadbooksell',
    querybooksell: httpHost + '/api/querybooksell',
    uploadbookbuy: httpHost + '/api/uploadbookbuy',
    allbookbuys: httpHost + '/api/allbookbuys',

    uploadorderdetail: httpHost + '/api/uploadorderdetail',
    deleteorderdetail: httpHost + '/api/deleteorderdetail',
    confirmorderinfo: httpHost + '/api/confirmorderinfo',
    cancelorderinfo: httpHost + '/api/cancelorderinfo',
    shoppingcart: httpHost + '/api/shoppingcart',
    userorderinfos: httpHost + '/api/userorderinfos',
    deleteuserorderinfos: httpHost + '/api/deleteuserorderinfos',
    deleteorderinfo: httpHost + '/api/deleteorderinfo',

    userbooksells: httpHost + '/api/userbooksells',
    deleteuserbooksells: httpHost + '/api/deleteuserbooksells',
    deletebooksell: httpHost + '/api/deletebooksell',
    userbookbuys: httpHost + '/api/userbookbuys',
    deleteuserbookbuys: httpHost + '/api/deleteuserbookbuys',
    deletebookbuy: httpHost + '/api/deletebookbuy',

    avatar: httpHost + '/api/avatar',
```

