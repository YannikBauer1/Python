const pecasTodas={00:"\uD83C\uDC31",01:"\uD83C\uDC32",02:"\uD83C\uDC33",03:"\uD83C\uDC34",04:"\uD83C\uDC35",05:"\uD83C\uDC36",06:"\uD83C\uDC37",10:"\uD83C\uDC38",11:"\uD83C\uDC39",12:"\uD83C\uDC3A",13:"\uD83C\uDC3B",14:"\uD83C\uDC3C",15:"\uD83C\uDC3D",16:"\uD83C\uDC3E",20:"\uD83C\uDC3F",21:"\uD83C\uDC40",22:"\uD83C\uDC41",23:"\uD83C\uDC42",24:"\uD83C\uDC43",25:"\uD83C\uDC44",26:"\uD83C\uDC45",30:"\uD83C\uDC46",31:"\uD83C\uDC47",32:"\uD83C\uDC48",33:"\uD83C\uDC49",34:"\uD83C\uDC4A",35:"\uD83C\uDC4B",36:"\uD83C\uDC4C",
                  40:"\uD83C\uDC4D",41:"\uD83C\uDC4E",42:"\uD83C\uDC4F",43:"\uD83C\uDC50",44:"\uD83C\uDC51",45:"\uD83C\uDC52",46:"\uD83C\uDC53",50:"\uD83C\uDC54",51:"\uD83C\uDC55",52:"\uD83C\uDC56",53:"\uD83C\uDC57",54:"\uD83C\uDC58",55:"\uD83C\uDC59",56:"\uD83C\uDC5A",60:"\uD83C\uDC5B",61:"\uD83C\uDC5C",62:"\uD83C\uDC5D",63:"\uD83C\uDC5E",64:"\uD83C\uDC5F",65:"\uD83C\uDC60",66:"\uD83C\uDC61"};
const pecasED={00:"\uD83C\uDC31",01:"\uD83C\uDC32",02:"\uD83C\uDC33",03:"\uD83C\uDC34",04:"\uD83C\uDC35",05:"\uD83C\uDC36",06:"\uD83C\uDC37",11:"\uD83C\uDC39",12:"\uD83C\uDC3A",13:"\uD83C\uDC3B",14:"\uD83C\uDC3C",15:"\uD83C\uDC3D",16:"\uD83C\uDC3E",22:"\uD83C\uDC41",23:"\uD83C\uDC42",24:"\uD83C\uDC43",25:"\uD83C\uDC44",26:"\uD83C\uDC45",33:"\uD83C\uDC49",34:"\uD83C\uDC4A",35:"\uD83C\uDC4B",36:"\uD83C\uDC4C",44:"\uD83C\uDC51",45:"\uD83C\uDC52",46:"\uD83C\uDC53",55:"\uD83C\uDC59",56:"\uD83C\uDC5A",66:"\uD83C\uDC61"};
const pecasDE={00:"\uD83C\uDC31",10:"\uD83C\uDC38",20:"\uD83C\uDC3F",30:"\uD83C\uDC46",40:"\uD83C\uDC4D",50:"\uD83C\uDC54",60:"\uD83C\uDC5B",11:"\uD83C\uDC39",21:"\uD83C\uDC40",31:"\uD83C\uDC47",41:"\uD83C\uDC4E",51:"\uD83C\uDC55",61:"\uD83C\uDC5C",22:"\uD83C\uDC41",32:"\uD83C\uDC48",42:"\uD83C\uDC4F",52:"\uD83C\uDC56",62:"\uD83C\uDC5D",33:"\uD83C\uDC49",43:"\uD83C\uDC50",53:"\uD83C\uDC57",63:"\uD83C\uDC5E",44:"\uD83C\uDC51",54:"\uD83C\uDC58",64:"\uD83C\uDC5F",55:"\uD83C\uDC59",65:"\uD83C\uDC60",66:"\uD83C\uDC61"};


const pecasServ={"[0,0]":00,"[0,1]":01,"[0,2]":02,"[0,3]":03,"[0,4]":04,"[0,5]":05,"[0,6]":06,"[1,0]":10,"[1,1]":11,"[1,2]":12,"[1,3]":13,"[1,4]":14,"[1,5]":15,"[1,6]":16,"[2,0]":20,"[2,1]":21,"[2,2]":22,"[2,3]":23,"[2,4]":24,"[2,5]":25,"[2,6]":26,"[3,0]":30,"[3,1]":31,"[3,2]":32,"[3,3]":33,"[3,4]":34,"[3,5]":35,"[3,6]":36,"[4,0]":40,"[4,1]":41,"[4,2]":42,"[4,3]":43,"[4,4]":44,"[4,5]":45,"[4,6]":46,"[5,0]":50,"[5,1]":51,"[5,2]":52,"[5,3]":53,"[5,4]":54,"[5,5]":55,"[5,6]":56,"[6,0]":60,"[6,1]":61,"[6,2]":62,"[6,3]":63,"[6,4]":64,"[6,5]":65,"[6,6]":66};


let pecasJogador=[];let pecasMonte=[];
let pecasComputador=[];
let pecasTabuleiro=[];
let difficuldade="";
let aJogar="";
let esqdirAjuda={};
let statN = [];
let statD =[];
let utilizador="";
let nick="";
let pass="";
let game="";
let interface = {
	efetuarLogin: function(){
		nick= document.getElementById("username").value;
		pass= document.getElementById("password").value;
		if (nick=="" || pass==""){
			alert("Efetue login novamente");
		}else{
			const url = 'http://twserver.alunos.dcc.fc.up.pt:8008/register';

			const data ={
				nick: nick,
				pass: pass
			};

			let fetchData = {
				method:"POST",
				body: JSON.stringify(data),
			}

			fetch(url, fetchData)
				.then(response => (response.text()))
				.then(res => interface.register(res, nick , pass))
				.catch(error => alert("ALGUMA COISA ESTÁ ERRADA", error	));
		}
	},

	register: function(res, nick,pass){
		if (res=="{}"){
			document.querySelector("#login").style.display="none";
			document.querySelector("#menu-1").style.display="block";
			document.querySelector("#menu-1").style.opacity=1;
			document.querySelector("#demo").innerHTML="Login por "+"<b>" + nick+"</b>";
			document.querySelector("#jogador").innerHTML=nick;
			document.getElementById("formulário").reset();
		} else if (res=='{"error":"User registered with a different password"}'){
			alert("Nome de utilizador ou password errada, tente novamente");
		}

	},

	mostrarRegras:function(){
		document.querySelector("#menu-1").style.display="none";
		document.querySelector("#regras").style.display="block";
		document.querySelector("#pag1").style.display="block";
		document.querySelector("#pag2").style.display="none";
		document.querySelector("#pag3").style.display="none";
		document.querySelector("#pag4").style.display="none";
	},
	
	pag1: function(){
		document.querySelector("#pag2").style.display="none";
		document.querySelector("#pag1").style.display="block";
	},
	voltarpag1: function(){
		document.querySelector("#pag2").style.display="none";
		document.querySelector("#pag1").style.display="block";
	},
	voltarpag2: function(){
		document.querySelector("#pag3").style.display="none";
		document.querySelector("#pag2").style.display="block";
	},
	voltarpag3: function(){
		document.querySelector("#pag4").style.display="none";
		document.querySelector("#pag3").style.display="block";
	},
	pag2:function(){
		document.querySelector("#pag1").style.display="none";
		document.querySelector("#pag2").style.display="block";
	},
	pag3:function(){
		document.querySelector("#pag2").style.display="none";
		document.querySelector("#pag3").style.display="block";
	},
	pag4:function(){
		document.querySelector("#pag3").style.display="none";
		document.querySelector("#pag4").style.display="block";
	},
	voltarInicio:function(){
		document.querySelector("#regras").style.display="none";
		document.querySelector("#menu-1").style.display="block";
		document.querySelector("#comandos").style.display="none";
	},
	mostrarTabuleiro:function(){
		document.querySelector("#menu-1").style.display="none";
		document.querySelector("#principal").style.display="block";
		document.querySelector("#dificuldades").style.display="none";
		document.querySelector("#principal").style.opacity=1;

		document.querySelector("#menu-2").style.pointerEvents="auto";

		funcJogar.dividePecas();
		funcJogar.mostrarPecas();
	},
	mostrarDificuldades: function(){
		document.querySelector("#menu-1").style.display="none";
		document.querySelector("#dificuldades").style.display="block";
		document.querySelector("#principal").style.display="block";
		document.querySelector("#principal").style.opacity=0.2;
	},
	mostrarComandos: function(){
		document.querySelector(".menuSec").style.display="none";
		document.querySelector("#comandos").style.display="block";
	},
	voltarMenu:function(){
		stat.deleteTabela();
		document.querySelector("#comandos").style.display="none";
		document.getElementById("estatisticas").style.display="none";
		document.querySelector(".menuSec").style.display="block";
	},
	mostrarMenu2: function(){
		document.querySelector(".menuSec").style.display="block";
		document.querySelector("#principal").style.opacity= 0.2;
		document.querySelector("#caixa1").style.pointerEvents="none";
		document.querySelector("#caixa2").style.pointerEvents="none";
		document.querySelector("#menu-2").style.pointerEvents="none";
	},
	voltarMenu2: function(){
		document.querySelector("#certeza").style.display="none";
		document.querySelector(".menuSec").style.display="block";
		document.querySelector("#menuJogo").style.display="block";
	},
	voltarJogo: function(){
		document.querySelector(".menuSec").style.display="none";
		document.querySelector("#principal").style.opacity= 1;
		document.querySelector("#menu-2").style.pointerEvents="auto";
		if(aJogar=="jogador"){
			funcJog.checkabilityMonte();
			funcJog.ableJogador();
			funcJog.checkAbilityPassarJog();
		};
	},

	certezaLogout: function(){
		document.getElementById("menu-1").style.opacity=0.2;
		document.getElementById("certezaLogout").style.display="block";
		document.querySelector("#menu-1 > ul").style.pointerEvents="none";
	},

	efetuarLogout: function(){
		document.getElementById("menu-1").style.display="none";
		document.getElementById("certezaLogout").style.display="none";
		document.getElementById("login").style.display="block";
		document.querySelector("#menu-1 > ul").style.pointerEvents="auto";
	},
	voltarMenu1: function(){
		document.getElementById("certezaLogout").style.display="none",
		document.getElementById("menu-1").style.opacity=1;
		document.querySelector("#menu-1 > ul").style.pointerEvents="auto";
	},

	logoutJogo: function(){
		document.getElementById("classificacao").style.display="none";
		document.getElementById("principal").style.display="none";
		document.getElementById("certezaLogout").style.display="block";
		endgame.resetDomino();
	},

	voltarMenuPrinc: function(){
		stat.deleteTabela();
		document.getElementById("classificacao").style.display="none";
		document.getElementById("principal").style.display="none";
		document.getElementById("menu-1").style.display="block";
		document.getElementById("novoJogo").style.display="block";
		endgame.resetDomino();
	},

	comandospag1:function(){
		document.getElementById("p1").style.display="block";
		document.getElementById("p2").style.display="none";
	},
	comandospag2: function(){
		document.getElementById("p1").style.display="none";
		document.getElementById("p2").style.display="block";
	},

	mostrarEstatística: function(){
		stat.mostrarTabela();
		document.querySelector(".menuSec").style.display="none";
		document.getElementById("estatisticas").style.display="block";
	},

	mostrarStat: function(){
		stat.mostrarTabela();
		document.getElementById("estatisticas").style.display="block";
		document.getElementById("classificacao").style.display="none";
		document.getElementById("voltarStatMenu").style.display="none";
		document.getElementById("voltarStatClass").style.display="block";
	},

	voltarMenuClass: function(){
		stat.deleteTabela()
		document.getElementById("estatisticas").style.display="none";
		document.getElementById("classificacao").style.display="block";
		document.getElementById("voltarStatMenu").style.display="block";
		document.getElementById("voltarStatClass").style.display="none";	
	},

	certezaDomino:function(){
		document.querySelector(".menuSec").style.display="none";
		document.querySelector("#certeza").style.display="block";
		document.querySelector("#certeza").style.opacity=1;
	},

	desistirJogo: function(){
		stat.addJogoToLocalStorage(0,endgame.calculePontosJog());
		endgame.resetDomino();
		document.querySelector(".menuSec").style.display="none";
		document.querySelector("#principal").style.display="none";
		document.querySelector("#principal").style.opacity= 1;
		document.querySelector("#menu-1").style.display="block";
		document.querySelector("#certeza").style.display="none";
	},

	novoJogo:function(){
		endgame.resetDomino();
		document.querySelector(".menuSec").style.display="none";
		document.querySelector("#classificacao").style.display="none";
		interface.mostrarDificuldades();
	},
	
	voltarClass: function(){
		console.log("FALTA FAZER");
	},

	jogvsjog: function(res){
		hand= res.hand;
		let j = document.getElementById("maoJogador");
		for (let i=0;i<7;i++){
			let s = document.createElement("span");
			s.setAttribute("onclick","funcJog.verificarJogada(this)");
			s.setAttribute("id", pecasServ[JSON.stringify(hand[i])]);
			s.setAttribute("onwheel","funcJog.rodarPeca(this);");
			s.setAttribute("class","peçaJog");
			s.setAttribute("draggable",true);
			j.appendChild(s);
			s.appendChild(document.createTextNode(pecasTodas[pecasServ[JSON.stringify(hand[i])]]));
			pecasJogador.push(pecasTodas[pecasServ[JSON.stringify(hand[i])]]);
		};
		game=res.game;
		funcJogar.update();
	},

	mostrarTabPVP: function(data){
		let m =	document.getElementById("Montee");
		let c = document.getElementById("Comp");
		let t = document.getElementById("jogo");
		let dic = data.board.count;

		delete dic[nick];

		let num = dic[Object.keys(dic)[0]];
		let adv = funcAjuda.getKeyByValue(dic,num)
		document.getElementById("computador").innerText = adv;


		document.getElementById("esperar").style.display="none";
		document.getElementById("principal").style.display="block";
		document.querySelector("#menu-2").style.pointerEvents="auto";
		document.getElementById("principal").style.opacity=1;
		document.getElementById("desistirComp").style.display="none";
		document.getElementById("desistirPVP").style.display="block";

		for (let i=100;i<data.board.stock+100;i++){
			let s = document.createElement("span");
			s.setAttribute("onclick","funcJog.pecaMonteToJogador()");
			s.setAttribute("id",i);
			s.setAttribute("class","peçaMonte");
			m.appendChild(s);
			s.appendChild(document.createTextNode("\uD83C\uDC30"));
			pecasMonte.push(i);
		};

		for (let i=200;i<num+200;i++){
			let s = document.createElement("span");
			s.setAttribute("id",i);
			s.setAttribute("class","peçaComp");
			c.appendChild(s);
			s.appendChild(document.createTextNode("\uD83C\uDC30"));
			pecasComputador.push(i);
		};

		let linha = data.board.line;
		for (let i=0;i<linha.length;i++){
			let s = document.createElement("span");
			s.setAttribute("id",pecasServ[JSON.stringify(linha[i])]);
			t.appendChild(s);
			s.appendChild(document.createTextNode(pecasTodas[pecasServ[JSON.stringify(linha[i])]]));
			pecasTabuleiro.push(pecasTodas[pecasServ[JSON.stringify(linha[i])]]);
		};

		if (data.turn==nick){
			funcJog.jogJoga();
			funcAjuda.gerarTexto("É a tua vez de jogar");
		} else{
			funcAjuda.gerarTexto("O outro jogador está a jogar");
		};

	}
};


let funcJogar ={
	//Comeca Aqui a parta do jogo em si
	dividePecas:function(){
		for (var key in pecasED){
			if (pecasED.hasOwnProperty(key)) { //tira a entidade das peças mas não mostra no tabuleiro
				pecasMonte.push(pecasED[key]);
			};		
		};
		for (let i=0;i<7;i++){
				let x=Math.floor(Math.random()*(28-i));
				pecasJogador.push(pecasMonte[x]);
				pecasMonte.splice(x,1);
		};
		for (let i=7;i<14;i++){
				let x=Math.floor(Math.random()*(28-i));
				pecasComputador.push(pecasMonte[x]);
				pecasMonte.splice(x,1);
		};
	},
	mostrarPecas:function(){
		let c = document.getElementById("Comp");
		let j = document.getElementById("maoJogador");
		let m =	document.getElementById("Montee");
		for (let i=0;i<7;i++){
			let s = document.createElement("span");
			s.setAttribute("onclick","funcJog.verificarJogada(this)");
			s.setAttribute("id",funcAjuda.getKeyByValue(pecasED,pecasJogador[i]));
			s.setAttribute("onwheel","funcJog.rodarPeca(this);");
			s.setAttribute("class","peçaJog");
			s.setAttribute("draggable",true);
			j.appendChild(s);
			s.appendChild(document.createTextNode(pecasJogador[i]));
		};
		for (let i=100;i<pecasMonte.length+100;i++){
			let s = document.createElement("span");
			s.setAttribute("onclick","funcJog.pecaMonteToJogador()");
			s.setAttribute("id",i);
			s.setAttribute("class","peçaMonte");
			m.appendChild(s);
			s.appendChild(document.createTextNode("\uD83C\uDC30"));
		};
		for (let i=0;i<7;i++){
			let s = document.createElement("span");
			s.setAttribute("id",funcAjuda.getKeyByValue(pecasED,pecasComputador[i]));
			s.setAttribute("class","peçaComp");
			c.appendChild(s);
			s.appendChild(document.createTextNode("\uD83C\uDC30"));
		};
		funcJogar.decideQuemComeca();
	},
	decideQuemComeca:function(){
		let valoresJog=[];
		let valoresComp=[];
		let maxValor=0;
		let indexJog=[];
		let indexComp=[];
		let e =0;
		let d =0;
		let x =0;
		for (let i of pecasJogador){
			e=funcAjuda.valorEsquerdoPeca(i);
			d=funcAjuda.valorDireitoPeca(i);
			x =e+d;
			valoresJog.push(x);
		};
		for (let i of pecasComputador){
			e=funcAjuda.valorEsquerdoPeca(i);
			d=funcAjuda.valorDireitoPeca(i);
			x =e+d;
			valoresComp.push(x);
		};
		for (i of valoresJog){
			if (i>maxValor){
				maxValor=i;
			};
		};
		for (i of valoresComp){
			if (i>maxValor){
				maxValor=i;
			};
		};
		for (let i =0;i<valoresJog.length;i++){
			if (valoresJog[i]==maxValor){
				indexJog.push(i);
			}
		};
		for (let i =0;i<valoresComp.length;i++){
			if (valoresComp[i]==maxValor){
				indexComp.push(i);
			}
		};
		let a=Math.floor(Math.random()*(indexComp.length+indexJog.length));
		if (a>(indexComp.length-1)){
			funcAjuda.gerarTexto("Jogador comeca");
			funcJogar.jogo("computador");
		}else if (a<indexComp.length){
			funcAjuda.gerarTexto("Computador comeca");
			funcJogar.jogo("jogador");
		};
	},
	jogo:function(jogou){
		let tI = funcAjuda.valorInicioTab();
		let tF = funcAjuda.valorFimTab();
		if(pecasTabuleiro.length>1){
			if(pecasJogador.length==0){
				endgame.classificaçoes("jogador");
			}else if (pecasComputador.length==0){
				endgame.classificaçoes("computador");
			}else if ((tI===tF) && (endgame.pecaApareceVezes(tI)==true)){
				endgame.classificaçoes(endgame.empateCalculeVencedor());
			}else if (endgame.ninguemPodeJogarMais()==true){
				endgame.classificaçoes(endgame.empateCalculeVencedor());
			}else{
				if(jogou=="jogador"){
					aJogar="computador";
					setTimeout(funcComp.compJoga, 2000);
				}else if (jogou=="computador"){
					aJogar="jogador";
					funcJog.jogJoga();
				};
			}
		}else{
			if(jogou=="jogador"){
				aJogar="computador";
				setTimeout(funcComp.compJoga, 2000);
			}else if (jogou=="computador"){
				aJogar="jogador";
				funcJog.jogJoga();
			};
		};
	},

	join: function(){
		document.getElementById("menu-1").style.display="none";
		document.getElementById("esperar").style.display="block";
		let group = "spiel";
		const url = 'http://twserver.alunos.dcc.fc.up.pt:8008/join';

			const data ={nick,pass,group};

			let fetchData = {
				method:"POST",
				body: JSON.stringify(data),
			}

			fetch(url, fetchData)
				.then(response => (response.json()))
				.then(res => interface.jogvsjog(res))
				.catch(error => alert("ALGUMA COISA ESTÁ ERRADA", error	));
		

	},

	sairEspera: function(){
		n=nick; p=pass;g=game;
		funcJogar.leave(n,p,g);
	},

	leave: function(n,p,g){
		const url = 'http://twserver.alunos.dcc.fc.up.pt:8008/leave';

		const data ={nick:n,pass:p,game:g};

		let fetchData = {
			method:"POST",
			body: JSON.stringify(data),
		}

		fetch(url, fetchData)
			.then(response => (response.json()))
			.then(function qlq(){
				/*
				document.getElementById("esperar").style.display="none";
				document.getElementById("menu-1").style.display="block";
				document.getElementById("desistirPVP").style.display="none";
				document.getElementById("desistirComp").style.display="block";
				document.getElementById("principal").style.display="none";
				document.getElementById("principal").style.opacity=1;
				document.querySelector(".menuSec").style.display="none";
				*/
			})
			.catch(error => console.log("ALGUMA COISA ESTÁ ERRADA", error));

	},
	update: function(){
		const url = "http://twserver.alunos.dcc.fc.up.pt:8008/update?nick="+nick+"&game="+game;
		const eventSource = new EventSource(url);
		eventSource.onmessage=function(event){
			const data=JSON.parse(event.data);
			console.log(data,"data do update");
			if (data.winner!=null){
				document.getElementById("classificacao").style.display="block";
				document.getElementById("principal").style.opacity=0.2;
				document.querySelector("#caixa2").style.pointerEvents="none";
				document.querySelector("#menu-2").style.pointerEvents ="none";
				document.querySelector(".menuSec").style.display="none";
				document.getElementById("desistirPVP").style.display="none";
				document.getElementById("desistirComp").style.display="block";
				document.getElementById("novoJogo").style.display="none";
				endgame.resetDomPVP();
				eventSource.close();
			} else if (data.winner==null && data.turn==null){
				document.getElementById("esperar").style.display="none";
				document.getElementById("menu-1").style.display="block";
				document.getElementById("desistirPVP").style.display="none";
				document.getElementById("desistirComp").style.display="block";
				eventSource.close();
			}else{
				endgame.limpa();
				interface.mostrarTabPVP(data);
			}
		}
	},

	notify: function(piece,side, skip){
		const url = 'http://twserver.alunos.dcc.fc.up.pt:8008/notify';

		const data ={nick:nick,pass:pass,game:game,piece:piece,side: side};


		let fetchData = {
			method:"POST",
			body: JSON.stringify(data),
		}

		fetch(url, fetchData)
			.then(response => (response.json()))
			.then(res =>{
				if (res.piece!= null ){
					let p = JSON.stringify(res.piece);
					p= pecasTodas[pecasServ[p]];
					let j = document.getElementById("maoJogador");
					let s = document.createElement("span");
					pecasJogador.push(p);
					s.setAttribute("id",funcAjuda.getKeyByValue(pecasTodas,p));
					s.setAttribute("class","peçaJog");
					s.appendChild(document.createTextNode(p));
					s.setAttribute("onclick","funcJog.verificarJogada(this);");
					s.setAttribute("onwheel","funcJog.rodarPeca(this);");
					j.append(s);
				} else{
					console.log(res);
				}
			})
			.catch(error => console.log("ALGUMA COISA ESTÁ ERRADA", error))
	},
};


let endgame ={
	calculePontosJog:function(){
		let j=0;
		for (i of pecasJogador){
			let e = funcAjuda.valorEsquerdoPeca(i);
			let d = funcAjuda.valorDireitoPeca(i);
			j=j+e+d;
		};
		return j;
	},
	calculePontosComp:function(){
		let c=0;
		for (i of pecasComputador){
			let e = funcAjuda.valorEsquerdoPeca(i);
			let d = funcAjuda.valorDireitoPeca(i);
			c=c+e+d;
		};
		return c;
	},
	empateCalculeVencedor:function(){
		let j=endgame.calculePontosJog();
		let c=endgame.calculePontosComp();
		if(j==c){
			return ("empate");
		}else if (c>j){
			return ("jogador");
		}else if (j>c){
			return ("computador");
		}
	},
	ninguemPodeJogarMais:function(){
		if (pecasMonte.length==0){
			let lista=[];
			let tI = funcAjuda.valorInicioTab();
			let tF = funcAjuda.valorFimTab();
			for (i of pecasComputador){
				lista.push(funcAjuda.valorEsquerdoPeca(i));
				lista.push(funcAjuda.valorDireitoPeca(i));
			};
			for (i of pecasJogador){
				lista.push(funcAjuda.valorEsquerdoPeca(i));
				lista.push(funcAjuda.valorDireitoPeca(i));
			};
			if ((lista.includes(tI)===false) && (lista.includes(tF)===false)){
				return true;
			};
		}else{
			return false;
		};
	},
	pecaApareceVezes:function(i){
		let c=0;
		for (j of pecasTabuleiro){
			let e = funcAjuda.valorEsquerdoPeca(j);
			let d = funcAjuda.valorDireitoPeca(j);
			if (i==e || i==d){
				c=c+1;
			}
		};
		if(c==7){
			return true;
		}else {
			return false;
		};
	},
	classificaçoes: function(player){
		stat.addJogoToLocalStorage(endgame.calculePontosComp(),endgame.calculePontosJog());
		document.getElementById("principal").style.opacity=0.2;
		document.querySelector("#caixa2").style.pointerEvents="none";
		document.querySelector("#menu-2").style.pointerEvents ="none";
		document.getElementById("classificacao").style.display="block";	
		if(player=="jogador"){
			document.getElementById("ganhar").innerText="Jogador!!! Parabens!!!!";
		}else if(player=="computador"){
			document.getElementById("ganhar").innerText="Computador.";
		};
		document.getElementById("pontos").innerText="Jogador tem "+endgame.calculePontosJog()+" pontos.Computador tem "+endgame.calculePontosComp()+" pontos";
	},
	resetDomino: function(){ 
		for(let i=100; i<pecasMonte.length+100;i++){
			let l =document.getElementById(i);
			l.remove();
		};
		for(let i=0; i<pecasJogador.length;i++){
			let key = funcAjuda.getKeyByValue(pecasTodas,pecasJogador[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		for(let i=0; i<pecasComputador.length;i++){
			let key = funcAjuda.getKeyByValue(pecasTodas,pecasComputador[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		for(let i=0; i<pecasTabuleiro.length;i++){
			let key = funcAjuda.getKeyByValue(pecasTodas,pecasTabuleiro[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		pecasMonte=[];
		pecasJogador=[];
		pecasComputador=[];
		pecasTabuleiro=[];
		aJogar="";
		funcAjuda.gerarTexto("");
		difficuldade="";
	},

	resetDomPVP: function(){
		for(let i=100; i<pecasMonte.length+100;i++){
			let l =document.getElementById(i);
			l.remove();
		};
		for(let i=0; i<pecasJogador.length;i++){
			let key = funcAjuda.getKeyByValue(pecasTodas,pecasJogador[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		for(let i=200; i<pecasComputador.length + 200;i++){
			let l =document.getElementById(i);
			l.remove();
		};
		for(let i=0; i<pecasTabuleiro.length;i++){
			let key = funcAjuda.getKeyByValue(pecasTodas,pecasTabuleiro[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		pecasMonte=[];
		pecasJogador=[];
		pecasComputador=[];
		pecasTabuleiro=[];
		funcAjuda.gerarTexto("");
		game="";
		document.getElementById("computador").innerText = "Computador";
	},

	limpa: function(){
		for(let i=100; i<pecasMonte.length+100;i++){
			let l =document.getElementById(i);
			l.remove();
		};
		for(let i=200; i<pecasComputador.length + 200;i++){
			let l =document.getElementById(i);
			l.remove();
		};
		for(let i=0; i<pecasTabuleiro.length;i++){
			let key = funcAjuda.getKeyByValue(pecasTodas,pecasTabuleiro[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		pecasMonte=[];
		pecasComputador=[];
		pecasTabuleiro=[];
	}
};

let funcJog={
	// Funcoes para Pecas do Jogador
	jogJoga:function(){
		funcJog.checkabilityMonte();
		funcJog.ableJogador();
		funcJog.checkAbilityPassarJog();
	},
	disableJogador:function(){
		document.getElementById("caixa1").style.pointerEvents = "none";
	},
	ableJogador:function(){
		document.getElementById("caixa1").style.pointerEvents = "auto";
	},
	disableMonte:function(){
		document.getElementById("caixa2").style.pointerEvents = "none";
	},
	checkabilityMonte:function(){
		let tI = funcAjuda.valorInicioTab();
		let tF = funcAjuda.valorFimTab();
		let numeros=[];
		for (i of pecasJogador){
			numeros.push(funcAjuda.valorDireitoPeca(i));
			numeros.push(funcAjuda.valorEsquerdoPeca(i));
		};
		if (tI=="free"){
			funcJog.disableMonte();
		}else if ((numeros.includes(tI)===false) && (numeros.includes(tF)===false)){
			document.getElementById("caixa2").style.pointerEvents = "auto";
		}else{
			funcJog.disableMonte();
		};
	},
	verificarJogada:function(a){
		funcJog.verificaPeca(a);
		let t= a.innerText;
		let e = funcAjuda.valorEsquerdoPeca(t);
		let d = funcAjuda.valorDireitoPeca(t);
		let tI = funcAjuda.valorInicioTab();
		let tF = funcAjuda.valorFimTab();
		if (tI=="free"){
			funcJog.toTabuleiroDireito(a);
		}else if (e==tF && d==tI){
			document.getElementById("escolhas").style.display="block";
			esqdirAjuda=a;
		}else if (d==tI){
			funcJog.toTabuleiroEsquerdo(a);
		} else if (e==tF){
			funcJog.toTabuleiroDireito(a);
		};
	},
	verificaPeca:function(a){
		let t = a.innerText;
		let tI = funcAjuda.valorInicioTab();
		let tF = funcAjuda.valorFimTab();
		let e = funcAjuda.valorEsquerdoPeca(t);
		let d = funcAjuda.valorDireitoPeca(t);
		if (e==tI || d==tF){
			funcAjuda.gerarTexto("Tens de rodar a peca para poder jogar");
		}else if (e!=tF && d!=tI){
			funcAjuda.gerarTexto("Nao podes jogar esta peca!");
		};
		
	},
	escolherladoEsquerdo:function(){
		funcJog.escolherlado("esquerdo");
	},
	escolherladoDireito:function(){
		funcJog.escolherlado("direito");
	},
	escolherlado:function(lado){
		let a = esqdirAjuda;
		if (lado=="direito"){
			funcJog.toTabuleiroDireito(a);
		}else if (lado=="esquerdo"){
			funcJog.toTabuleiroEsquerdo(a);
		};
		document.getElementById("escolhas").style.display="none";
	},
	toTabuleiroEsquerdo:function(a){
		if (game==""){
		    let t = document.getElementById("jogo");
		    let s = document.createElement("span");
		    let c= a.innerText;
		    let key = funcAjuda.getKeyByValue(pecasTodas,c);
		    let d = document.getElementById(key);
		    d.remove();
		    s.setAttribute("id",funcAjuda.getKeyByValue(pecasTodas,c));
		    //s.setAttribute("class","peçaTabuleiro");
		    t.insertBefore(s, t.firstChild);
		    s.appendChild(document.createTextNode(c));
		    let i = pecasJogador.indexOf(c);
		    pecasJogador.splice(i,1);
		    pecasTabuleiro.unshift(c);
		    funcJog.disableJogador();
		    funcJog.disableMonte();
		    funcJogar.jogo("jogador");
		} else {

			let t = document.getElementById("jogo");
	    	//let s = document.createElement("span");
		    let c= a.innerText;
			let key = funcAjuda.getKeyByValue(pecasTodas,c);
			
		    let d = document.getElementById(key);
		    d.remove();
		    let i = pecasJogador.indexOf(c);
		    pecasJogador.splice(i,1);
		    funcJog.disableJogador();
			funcJog.disableMonte();

			let e = funcAjuda.valorKeyEsquerdo(key);
			if (e==0){
				key="0"+key;
			}


			let pecS =key.split('');
			let pec = Array.from(pecS,Number);
			funcJogar.notify(pec,"start",null);
		}
		funcAjuda. gerarTexto("Adversário esta a jogar");
	},
	toTabuleiroDireito:function(a){
		if (game==""){
			let t = document.getElementById("jogo");
			let s = document.createElement("span");
			let c= a.innerText;
			let key = funcAjuda.getKeyByValue(pecasTodas,c);
			let d = document.getElementById(key);
			d.remove();
			s.setAttribute("id",funcAjuda.getKeyByValue(pecasTodas,c));
			t.appendChild(s);
			s.appendChild(document.createTextNode(c));
			let i = pecasJogador.indexOf(c);
			pecasJogador.splice(i,1);
			pecasTabuleiro.push(c);
			funcJog.disableJogador();
			funcJog.disableMonte();
			funcJogar.jogo("jogador");
			funcAjuda. gerarTexto("Computador esta a jogar");
		} else{
			let t = document.getElementById("jogo");
		    let c= a.innerText;
		    let key = funcAjuda.getKeyByValue(pecasTodas,c);
		    let d = document.getElementById(key);
		    d.remove();
		    let i = pecasJogador.indexOf(c);
		    pecasJogador.splice(i,1);
		    funcJog.disableJogador();
			funcJog.disableMonte();

			let e = funcAjuda.valorKeyEsquerdo(key);
			if (e==0){
				key="0"+key;
			}

			let pecS = key.split('');
			let pec = Array.from(pecS,Number);
			funcJogar.notify(pec,"end",null);
		}
		funcAjuda. gerarTexto("Adversário esta a jogar");
	},
	pecaMonteToJogador:function(){
		if (game==""){
			let j = document.getElementById("maoJogador");
			let s = document.createElement("span");
			let l = pecasMonte.length;
			let x = Math.floor(Math.random()*(l))
			let c = pecasMonte[x];
			pecasMonte.splice(x,1);
			pecasJogador.push(c);
			let d = document.getElementById(l+99);
			d.remove();
			s.setAttribute("id",funcAjuda.getKeyByValue(pecasED,c));
			s.setAttribute("class","peçaJog");
			s.appendChild(document.createTextNode(c));
			s.setAttribute("onclick","funcJog.verificarJogada(this);");
			s.setAttribute("onwheel","funcJog.rodarPeca(this);");
			j.append(s);
			funcJog.checkabilityMonte();
			funcJog.checkAbilityPassarJog();
		} else{
			funcJogar.notify(null,null,null);
		}
	},
	rodarPeca:function(a){
		let t= a.innerText;
		let e = funcAjuda.valorEsquerdoPeca(t).toString();
		let d = funcAjuda.valorDireitoPeca(t).toString();
		let key=d+e;
		if (d==0){
			key=e;
		};
		let novot=pecasTodas[key];
		a.innerText=novot;
		a.id = key;
		let i = pecasJogador.indexOf(t);
		pecasJogador.splice(i,1);
		pecasJogador.splice(i,0,novot);
	},
	checkAbilityPassarJog: function(){
		let tI = funcAjuda.valorInicioTab();
		let tF = funcAjuda.valorFimTab();
		let numeros=[];
		if (pecasMonte.length==0){
			for (i of pecasJogador){
			numeros.push(funcAjuda.valorDireitoPeca(i));
			numeros.push(funcAjuda.valorEsquerdoPeca(i));
			};
			if ((numeros.includes(tI)===false) && (numeros.includes(tF)===false)){
				let e = document.getElementById("passaJogada");
				e.disabled=false;
			};
		};
	},
	passarJogadaJog: function(){
		funcJogar.jogo("jogador");
		funcAjuda. gerarTexto("Computador esta a jogar");
		funcJog.passarJogadaOff();
		
	},

	passarJogadaOff: function(){
		let e = document.getElementById("passaJogada");
		e.disabled=true;
	},

};

let funcComp={
	
	
	// Funcoes para Pecas do Computador
	compJoga:function(){
		let tI = funcAjuda.valorInicioTab();
		let tF = funcAjuda.valorFimTab();
		let pecasPossiveis=[];
		if (tI=="free"){
			if (difficuldade=="dificil"){
				let key=funcComp.difficuldadeDificil(pecasComputador);
				funcComp.compToTabuleiroDireito(key);
			}else if (difficuldade=="facil"){
				let key=funcComp.difficuldadeFacil(pecasComputador);
				funcComp.compToTabuleiroDireito(key);
			};
		}else {
			for (i of pecasComputador){
				let e=funcAjuda.valorEsquerdoPeca(i);
				let d=funcAjuda.valorDireitoPeca(i);
				if (e==tF || d==tI){
					pecasPossiveis.push(i);
				} else if(e==tI || d==tF){
					pecasPossiveis.push(i);	
				};
			};
			if ((pecasPossiveis.length==[])&&(pecasMonte.length==0)){
				funcJogar.jogo("computador");
				funcAjuda.gerarTexto("É a tua vez");
			} else if ((pecasPossiveis.length==[])&&(pecasMonte.length>0)){
				funcComp.pecaMonteToComp();
				funcComp.compJoga();
			}else{
				if (difficuldade=="dificil"){
					let k=funcComp.difficuldadeDificil(pecasPossiveis);
					funcComp.computadorPecaVerifica(k);
				}else if (difficuldade=="facil"){
					let k=funcComp.difficuldadeFacil(pecasPossiveis);
					funcComp.computadorPecaVerifica(k);
				};
			};
		};
	},
	pecaMonteToComp:function(){
		let j = document.getElementById("Comp");
		let s = document.createElement("span");
		let l = pecasMonte.length;
		let x = Math.floor(Math.random()*(l))
		let c = pecasMonte[x];
		pecasMonte.splice(x,1);
		pecasComputador.push(c);
		let d = document.getElementById(l+99);
		d.remove();
		s.setAttribute("id",funcAjuda.getKeyByValue(pecasED,c));
		s.setAttribute("class","peçaComp");
		s.appendChild(document.createTextNode("\uD83C\uDC30"));
		j.append(s);
	},
	computadorPecaVerifica:function(key){
		let a=pecasTodas[key];
		let tI = funcAjuda.valorInicioTab();
		let tF = funcAjuda.valorFimTab();
		let e=funcAjuda.valorEsquerdoPeca(a);
		let d=funcAjuda.valorDireitoPeca(a);
		if (d==tI){
			funcComp.compToTabuleiroEsquerdo(key);
		} else if (e==tF){
			funcComp.compToTabuleiroDireito(key);
		} else if(e==tI){
			e=e.toString();
			d=d.toString();
			let keynovo=d+e;
			let pecaInv=pecasTodas[keynovo];
			document.getElementById(key).id=keynovo;
			let i =pecasComputador.indexOf(a);
			pecasComputador[i]=pecaInv;
			funcComp.compToTabuleiroEsquerdo(keynovo);
		} else if(d==tF){
			e=e.toString();
			d=d.toString();
			let keynovo=d+e;
			let pecaInv=pecasTodas[keynovo];
			let element=document.getElementById(key);
			element.id=keynovo;
			let i =pecasComputador.indexOf(a);
			pecasComputador[i]=pecaInv;
			funcComp.compToTabuleiroDireito(keynovo);
		};
	},
	compToTabuleiroDireito:function(key){
		let ele = document.getElementById(key);
		let t = document.getElementById("jogo");
		let s = document.createElement("span");
		let c= pecasTodas[key];
		ele.remove();
		s.setAttribute("id",key);
		t.appendChild(s);
		s.appendChild(document.createTextNode(c));
		let i = pecasComputador.indexOf(c);
		pecasComputador.splice(i,1);
		pecasTabuleiro.push(c);
		funcJogar.jogo("computador");
		funcAjuda.gerarTexto("É a tua vez");
	},
	compToTabuleiroEsquerdo:function(key){
		let ele = document.getElementById(key);
		let t = document.getElementById("jogo");
		let s = document.createElement("span");
		let c= pecasTodas[key];
		ele.remove();
		s.setAttribute("id",key);
		t.insertBefore(s, t.firstChild);
		s.appendChild(document.createTextNode(c));
		let i = pecasComputador.indexOf(c);
		pecasComputador.splice(i,1);
		pecasTabuleiro.unshift(c);
		funcJogar.jogo("computador");
		funcAjuda.gerarTexto("É a tua vez");
	},
	setDifficuldadeFacil:function(){
		difficuldade="facil";
	},
	setDifficuldadeDificil:function(){
		difficuldade="dificil";
	},
	difficuldadeFacil:function(lista){
		let lll=lista;
		let key=0;
		let i =0;
		let x =0;
		let l=[];
		let lajuda=[];
		if(lista.length==1){
			key = funcAjuda.getKeyByValue(pecasTodas,lll[0]);
			return(key)
		}else{
			for (i of lista){
				let e=funcAjuda.valorEsquerdoPeca(i);
				let d=funcAjuda.valorDireitoPeca(i);
				x=e+d;
				l.push(x);
			};
			let min=12;
			for (i of l){
				if (i<min){
					min=i;
				};
			}
			for (i=0;i<l.length;i++){
				if(min==l[i]){
					lajuda.push(i);
				};
			}
			let z = Math.floor(Math.random()*(lajuda.length))
			let y= lajuda[z];
			key = funcAjuda.getKeyByValue(pecasTodas,lll[y]);
			return (key);
		};
	},
	difficuldadeDificil:function(lista){
		let lll=lista;
		let key=0;
		let i =0;
		let x=0;
		let l=[];
		let lajuda=[];
		if(lista.length==1){
			key = funcAjuda.getKeyByValue(pecasTodas,lll[0]);
			return(key)
		}else{
			for (i of lista){
				let e=funcAjuda.valorEsquerdoPeca(i);
				let d=funcAjuda.valorDireitoPeca(i);
				x=e+d;
				l.push(x);
			};
			let max=0;
			for (i of l){
				if (i>max){
					max=i;
				};
			}
			for (i=0;i<l.length;i++){
				if(max==l[i]){
					lajuda.push(i);
				};
			}
			let z = Math.floor(Math.random()*(lajuda.length))
			let y= lajuda[z];
			key = funcAjuda.getKeyByValue(pecasTodas,lll[y]);
			return (key);
		};
	}
};

let funcAjuda={
	// Funcoes complementar
	valorKeyEsquerdo:function(x){
		if (x<10){
			return 0;
		} else if (x<20){
			return 1;
		} else if (x<30){
			return 2;
		} else if (x<40){
			return 3;
		} else if (x<50){
			return 4;
		} else if (x<60){
			return 5;
		} else {
			return 6;
		};
	},
	valorKeyDireito:function(x){
		let z=x%10;
		if (z==0){
			return 0;
		} else if (z==1){
			return 1;
		} else if (z==2){
			return 2;
		} else if (z==3){
			return 3;
		} else if (z==4){
			return 4;
		} else if (z==5){
			return 5;
		} else {
			return 6;
		};
	},
	valorInicioTab:function(){
		let x=pecasTabuleiro.length;
		if (x==0){
			return "free"
		} else{
			let i=funcAjuda.getKeyByValue(pecasTodas,pecasTabuleiro[0]);
			return(funcAjuda.valorKeyEsquerdo(i));
		};
	},
	valorFimTab:function(){
		let x=pecasTabuleiro.length;
		if (x==0){
			return "free"
		} else{
			let i=funcAjuda.getKeyByValue(pecasTodas,pecasTabuleiro[pecasTabuleiro.length-1]);
			return(funcAjuda.valorKeyDireito(i));
		};
	},
	valorEsquerdoPeca:function(x){
		let i=funcAjuda.getKeyByValue(pecasTodas,x);
		return(funcAjuda.valorKeyEsquerdo(i));
	},
	valorDireitoPeca:function(x){
		let i=funcAjuda.getKeyByValue(pecasTodas,x);
		return(funcAjuda.valorKeyDireito(i));
	},
	pecaInversa:function(peca){
		let e=funcAjuda.valorEsquerdoPeca(peca).toString();
		let d=funcAjuda.valorDireitoPeca(peca).toString();
		let key=d+e;
		let inversa=pecasTodas[key];
		return (inversa);
	},
	getKeyByValue: function(object, value) {
		return Object.keys(object).find(key => object[key] === value);
	  },
	gerarTexto: function(txt){
		document.getElementById("texto").innerText=txt;	
	},
};

let stat={
	addJogoToLocalStorage:function(comp,jog){
		let l=localStorage.length;
		let i=localStorage.getItem(utilizador);
		let c=comp.toString();
		let j=jog.toString();
		let s="";
		if (i==null){
			s=c+" "+j+" "+difficuldade;
		}else{
			s=i+","+c+" "+j+" "+difficuldade;
		};
		localStorage.setItem(utilizador,s);
	},
	mostrarTabela: function(){
		stat.ranking();
		let tudo=localStorage.getItem(utilizador);
		if (tudo==null){}else{
			let a=tudo.split(",");
			let ajuda=[];
			let jogos=[];
			for(let i=0;i<a.length;i++){
				ajuda=a[i].split(" ");
				if(ajuda.includes("facil")==true){
					jogos.push(parseInt(ajuda[0]),parseInt(ajuda[1]));
					statN.push(jogos);
					jogos=[];
				}else if (ajuda.includes("dificil")==true){
					jogos.push(parseInt(ajuda[0]),parseInt(ajuda[1]));
					statD.push(jogos);
					jogos=[];
				}
			};
			let tabN =document.querySelector("#tabNormal");
			let tabD =document.querySelector("#tabDificil");
			
			for(var i=0;i<statN.length;i++){
				let iNovo=i+1;
				let arrayJogo=statN[i];
				let row = tabN.insertRow(i+1);
				let c1=row.insertCell(0);
				let c2=row.insertCell(1);
				let c3=row.insertCell(2);
				c1.innerHTML="Jogo "+ iNovo;
				c2.innerHTML = arrayJogo[0];
				c3.innerHTML = arrayJogo[1];
				let list = stat.totalJogadoresN();
				let cComp=list[0];
				let cJog= list[1];
				document.getElementById("CompTotalN").innerText=cComp;
				document.getElementById("JogTotalN").innerText=cJog;
			};
			for(var i=0;i<statD.length;i++){
				let iNovo=i+1;
				let arrayJogo=statD[i];
				let row = tabD.insertRow(i+1);
				let c1=row.insertCell(0);
				let c2=row.insertCell(1);
				let c3=row.insertCell(2);
				c1.innerHTML="Jogo "+ iNovo;
				c2.innerHTML = arrayJogo[0];
				c3.innerHTML = arrayJogo[1];
				let list = stat.totalJogadoresD();
				let cComp=list[0];
				let cJog= list[1];
				document.getElementById("CompTotalD").innerText=cComp;
				document.getElementById("JogTotalD").innerText=cJog;
			}
		}
	},
	totalJogadoresD: function(){
		let cComp=0;
		let cJog=0;
		let l =[];
		
		for(i of statD){
			cComp+=i[0];
			cJog+=i[1];
		}
		l.push(cComp, cJog);
		return l;
	},
	totalJogadoresN: function(){
		let cComp=0;
		let cJog=0;
		let l =[];
		
		for(i of statN){
			cComp+=i[0];
			cJog+=i[1];
		}
		l.push(cComp, cJog);
		return l;
	},

	deleteTabela: function(){
		let tabN =document.getElementById("tabNormal");
		let tabD =document.getElementById("tabDificil");
		for (let i=statN.length;i>0;i--){
			tabN.deleteRow(i);
		};
		for (let i=statD.length;i>0;i--){
			tabD.deleteRow(i);
		};
		statN=[];
		statD=[];
		stat.deleteRanking();
	},

	ranking: function(){
		const url = 'http://twserver.alunos.dcc.fc.up.pt:8008/ranking';
		let username= document.getElementById("username").value;
		let password= document.getElementById("password").value;
		let data ={
			nick: username,
			pass: password
		};

		const newUser = (e)=>{
			e.preventDefault();
		}

		let fetchData = {
			method:"POST",
			body: JSON.stringify(data),
		}

		fetch(url, fetchData)
		.then(resp => resp.json())
			.then(res => stat.mostrarRanking(res))
			.catch(function(error){
			console.log("Request failure: ", error);
		});
	},

	mostrarRanking: function(a){
		tabRank=document.getElementById("rankingID")
		for(var i=0;i<a.ranking.length;i++){
			let row = tabRank.insertRow(i+1);
			let c1=row.insertCell(0);
			let c2=row.insertCell(1);
			let c3=row.insertCell(2);
			c1.innerHTML=a.ranking[i].nick;
			c2.innerHTML =a.ranking[i].victories;
			c3.innerHTML = a.ranking[i].games;
		};
	},
	deleteRanking:function(){
		let tabRank =document.getElementById("rankingID");
		for (let i=tabRank.rows.length-1;i>0;i--){
			tabRank.deleteRow(i);
		};
	}

};

