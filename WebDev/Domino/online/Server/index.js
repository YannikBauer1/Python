const http = require("http");
const path = require("path");
const url = require("url");
const fs = require("fs");
const port = 8103;

const server = http.createServer((req,res)=> {
	console.log("hello")
	res.end();
	});
server.listen(port,"twserver.alunos.dcc.fc.up.pt");