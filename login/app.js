const express=require('express');
const cors=require("cors")
const app=express(); 
app.use(cors());
const cookieParser = require("cookie-parser");
const sessions = require('express-session');
const oneDay = 1000 * 60 * 60 * 24;

app.use(sessions({
    secret: "thisismysecrctekeyfhrgfgrfrty84fwir767",
    saveUninitialized:true,
    cookie: { maxAge: oneDay },
    resave: false
}));
app.use(cookieParser())
app.use(express.urlencoded({extended:false}));
app.use(express.json())
app.set('view engine', 'ejs');
const {Authrouter}=require('./controllers/routehandler.js')
app.use('/',Authrouter)
app.listen(8000, () => console.log("listening at port 8000"));