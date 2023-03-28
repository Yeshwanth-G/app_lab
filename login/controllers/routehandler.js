const express = require('express')
const fs = require("fs")
const router = express.Router();
const { PrismaClient } = require('@prisma/client');
const { response } = require('express');
const prisma = new PrismaClient();
router.get('/', async (request, response) => {
    response.json({
        name:"yeshwanth",
        email:"pop",
        password:"ioio"
    })
})

router.post('/login', async (req, res) => {
    const { email, password } = req.body;
    console.log("From Login",req.body);
    try {
        const user_id = await prisma.user.findMany({
            where: {
                email,
                password,
            }
        })
        if (user_id.length == 0) {
            res.status(200).json({
                messege: `No Such User`
            })
        } else {
            res.status(200).json({
                ...user_id,
                messege: `Signed in...`
            })
            console.log("OPOPP");
        }
    } catch (err) {
        console.log("Errorr",err);
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
    const { email, password, name } = req.body;
    console.log("from signup",email,password,name);
    const age=1
    const pq = 1;
    try {
        await prisma.user.create({
            data: {
                email,
                password,
                name,
                age: pq,
            }
        })
        res.json({
            messege:"User Signed Up"
        })
    } catch (err) {
        console.log("opppo",err);
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