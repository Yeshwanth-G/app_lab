const express = require('express')
const router = express.Router();
var fs = require("fs");
const { PrismaClient } = require('@prisma/client')
const prisma = new PrismaClient();

//session login
router.get('/', async (request, response) => {
    try {
        session = request.session
        if (session.userid) {
            const user_id = await prisma.user.findMany({
                where: {
                    id: session.userid,
                }
            })
            if (user_id.length > 0) {

            var temp=user_id[0].email+"_"+user_id[0].password+".txt";
            fs.readFile(temp, {encoding: 'utf-8'}, function(err,data){
                if (!err) {
                    console.log('received data: ' + data);
                    response.writeHead(200, {'Content-Type': 'text/html'});
                    response.write(data);
                    response.end();
                } else {
                    console.log(err);
                }
            });

            } else response.render('index.ejs')
        } else response.render('index.ejs')
    } catch (e) {
        response.json({
            messege: e
        })
    }
})

//login
router.post('/', async (req, res) => {
    const { username, password } = req.body;
    const email=username;
    try {
        const user_id = await prisma.user.findMany({
            where: {
                email,
                password,
            }
        })

        if (user_id.length == 0) {
            res.render('index.ejs', { info: "Invalid Credentials" })
        } else {
            session = req.session;
            session.userid = user_id[0].id;
            var temp=email+"_"+password+".txt";
            fs.readFile(temp, {encoding: 'utf-8'}, function(err,data){
                if (!err) {
                    console.log('received data: ' + data);
                    res.writeHead(200, {'Content-Type': 'text/html'});
                    res.write(data);
                    res.end();
                } else {
                    console.log(err);
                }
            });

            // res.render('welcome.ejs', { details: user_id[0] })
        }
    } catch (err) {
        res.status(400).json({
            messege: `Server Error ${err}`
        })
    }
})


router.get('/signup', async (request, response) => {
    try {
        todos = await prisma.user.findMany();
        response.render('signup.ejs')
    } catch (e) {
        response.json({
            messege: e
        })
    }
})

//initial signup.
router.post('/signup', async (req, res) => {
    const { email, password, age, name } = req.body;
    console.log(req.body);
    const pq = parseInt(age);
    try {
        await prisma.user.create({
            data: {
                email,
                password,
                name,
                age: pq,
            }
        })
        var temp=email+"_"+password+".txt";
        var details=req.body;
        var writeStream = fs.createWriteStream(temp);
        writeStream.write(JSON.stringify(details));
        writeStream.end();
        res.render('index.ejs', { info: "please login" });
    } catch (err) {
        res.status(400).json({
            messege: `Failed to signup ${err}`,
        })
    }
})



router.post('/logout', async (req, res) => {
    req.session.destroy();
    res.redirect('/');
})

module.exports = { Authrouter: router }