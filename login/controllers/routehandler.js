const express = require('express')
const router = express.Router();
const { PrismaClient } = require('@prisma/client')
const prisma = new PrismaClient();
router.get('/', async (request, response) => {
    try {
        session = request.session
        console.log("from get",session);
        if (session.userid) {
            const user_id = await prisma.user.findMany({
                where: {
                    id:session.userid,
                }
            })
            if (user_id.length>0) {
                response.render('welcome.ejs', { details: user_id[0] })
            }else response.render('index.ejs') 
        } else response.render('index.ejs')
    } catch (e) {
        response.json({
            messege: e
        })
    }
})

router.post('/', async (req, res) => {
    const { email, password } = req.body;
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
        session=req.session;
        session.userid=user_id[0].id;
        console.log(req.session)
            res.render('welcome.ejs', { details: user_id[0] })
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