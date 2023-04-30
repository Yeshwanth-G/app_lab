const express=require('express');
const cors=require("cors")
const app=express(); 
app.use(cors());
const cookieParser = require("cookie-parser");
const sessions = require('express-session');
const oneDay = 1000 * 60 * 60 * 24;
app.use(express.urlencoded({extended:false}));
app.use(express.json())
app.set('view engine', 'ejs');
app.use((req,res,next)=>{
    console.log("req recieved...");
    next();
})
const {Authrouter}=require('./controllers/routehandler.js')
app.use('/',Authrouter)
app.listen(8000, () => console.log("listening at port 8000"));