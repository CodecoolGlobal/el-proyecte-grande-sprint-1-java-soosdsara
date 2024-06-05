const express = require('express')
const { createProxyMiddleware } = require ('http-proxy-middleware')


const app = express();
const port = process.env.PORT || 3000;

// Serve static files from the "dist" directory
app.use('/api', createProxyMiddleware({
    target: 'http://pokemon-backend-cont:8080/api', //dockercontainer name
    changeOrigin: true
}));

app.use(express.urlencoded({extended: true}))
app.use(express.json())
app.use(express.static('public'))



app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});