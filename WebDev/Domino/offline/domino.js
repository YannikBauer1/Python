const pecasTodas={00:"\uD83C\uDC31",01:"\uD83C\uDC32",02:"\uD83C\uDC33",03:"\uD83C\uDC34",04:"\uD83C\uDC35",05:"\uD83C\uDC36",06:"\uD83C\uDC37",10:"\uD83C\uDC38",11:"\uD83C\uDC39",12:"\uD83C\uDC3A",13:"\uD83C\uDC3B",14:"\uD83C\uDC3C",15:"\uD83C\uDC3D",16:"\uD83C\uDC3E",20:"\uD83C\uDC3F",21:"\uD83C\uDC40",22:"\uD83C\uDC41",23:"\uD83C\uDC42",24:"\uD83C\uDC43",25:"\uD83C\uDC44",26:"\uD83C\uDC45",30:"\uD83C\uDC46",31:"\uD83C\uDC47",32:"\uD83C\uDC48",33:"\uD83C\uDC49",34:"\uD83C\uDC4A",35:"\uD83C\uDC4B",36:"\uD83C\uDC4C",
                  40:"\uD83C\uDC4D",41:"\uD83C\uDC4E",42:"\uD83C\uDC4F",43:"\uD83C\uDC50",44:"\uD83C\uDC51",45:"\uD83C\uDC52",46:"\uD83C\uDC53",50:"\uD83C\uDC54",51:"\uD83C\uDC55",52:"\uD83C\uDC56",53:"\uD83C\uDC57",54:"\uD83C\uDC58",55:"\uD83C\uDC59",56:"\uD83C\uDC5A",60:"\uD83C\uDC5B",61:"\uD83C\uDC5C",62:"\uD83C\uDC5D",63:"\uD83C\uDC5E",64:"\uD83C\uDC5F",65:"\uD83C\uDC60",66:"\uD83C\uDC61"};
const pecasED={00:"\uD83C\uDC31",01:"\uD83C\uDC32",02:"\uD83C\uDC33",03:"\uD83C\uDC34",04:"\uD83C\uDC35",05:"\uD83C\uDC36",06:"\uD83C\uDC37",11:"\uD83C\uDC39",12:"\uD83C\uDC3A",13:"\uD83C\uDC3B",14:"\uD83C\uDC3C",15:"\uD83C\uDC3D",16:"\uD83C\uDC3E",22:"\uD83C\uDC41",23:"\uD83C\uDC42",24:"\uD83C\uDC43",25:"\uD83C\uDC44",26:"\uD83C\uDC45",33:"\uD83C\uDC49",34:"\uD83C\uDC4A",35:"\uD83C\uDC4B",36:"\uD83C\uDC4C",44:"\uD83C\uDC51",45:"\uD83C\uDC52",46:"\uD83C\uDC53",55:"\uD83C\uDC59",56:"\uD83C\uDC5A",66:"\uD83C\uDC61"};
const pecasDE={00:"\uD83C\uDC31",10:"\uD83C\uDC38",20:"\uD83C\uDC3F",30:"\uD83C\uDC46",40:"\uD83C\uDC4D",50:"\uD83C\uDC54",60:"\uD83C\uDC5B",11:"\uD83C\uDC39",21:"\uD83C\uDC40",31:"\uD83C\uDC47",41:"\uD83C\uDC4E",51:"\uD83C\uDC55",61:"\uD83C\uDC5C",22:"\uD83C\uDC41",32:"\uD83C\uDC48",42:"\uD83C\uDC4F",52:"\uD83C\uDC56",62:"\uD83C\uDC5D",33:"\uD83C\uDC49",43:"\uD83C\uDC50",53:"\uD83C\uDC57",63:"\uD83C\uDC5E",44:"\uD83C\uDC51",54:"\uD83C\uDC58",64:"\uD83C\uDC5F",55:"\uD83C\uDC59",65:"\uD83C\uDC60",66:"\uD83C\uDC61"};
let pecasJogador=[];
let pecasMonte=[];
let pecasComputador=[];
let pecasTabuleiro=[];
let difficuldade="";
let aJogar="";
let esqdirAjuda={};
let statN = [];
let statD =[];
let gameManager = {
	efetuarLogin:function(){
		let username=document.querySelector("#username").value;
		let password= document.querySelector("#password").value;
		if (username==""){
			alert("Efetue login novamente");
		}
		else{
			document.querySelector("#login").style.display="none";
			document.querySelector("#menu-1").style.display="block";
			document.querySelector("#menu-1").style.opacity=1;
			document.querySelector("#demo").innerHTML="Login por "+"<b>" + username+"</b>";
			document.querySelector("#jogador").innerHTML=username;
			}
			document.getElementById("formulário").reset();

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

		gameManager.dividePecas();
		gameManager.mostrarPecas();
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
			gameManager.checkabilityMonte();
			gameManager.ableJogador();
			gameManager.checkAbilityPassarJog();
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
		gameManager.resetDomino();
	},

	voltarMenuPrinc: function(){
		document.getElementById("classificacao").style.display="none";
		document.getElementById("principal").style.display="none";
		document.getElementById("menu-1").style.display="block";
		gameManager.resetDomino();
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
		document.querySelector(".menuSec").style.display="none";
		document.getElementById("estatisticas").style.display="block";
	},

	mostrarStat: function(){
		console.log("fffff");
		document.getElementById("estatisticas").style.display="block";
		document.getElementById("classificacao").style.display="none";
		document.getElementById("voltarStatMenu").style.display="none";
		document.getElementById("voltarStatClass").style.display="block";
	},

	voltarMenuClass: function(){
		document.getElementById("estatisticas").style.display="none";
		document.getElementById("classificacao").style.display="block";
		document.getElementById("voltarStatMenu").style.display="block";
		document.getElementById("voltarStatClass").style.display="none";	
	},



	//Comeca Aqui a parta do jogo em si
	dividePecas:function(){
		for (var key in pecasED){
			if (pecasED.hasOwnProperty(key)) { 
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
			s.setAttribute("onclick","gameManager.verificarJogada(this)");
			s.setAttribute("id",getKeyByValue(pecasED,pecasJogador[i]));
			s.setAttribute("onwheel","gameManager.rodarPeca(this);");
			s.setAttribute("class","peçaJog");
			s.setAttribute("draggable",true);
			j.appendChild(s);
			s.appendChild(document.createTextNode(pecasJogador[i]));
		};
		for (let i=100;i<pecasMonte.length+100;i++){
			let s = document.createElement("span");
			s.setAttribute("onclick","gameManager.pecaMonteToJogador()");
			s.setAttribute("id",i);
			s.setAttribute("class","peçaMonte")
			m.appendChild(s);
			s.appendChild(document.createTextNode("\uD83C\uDC30"));
		};
		for (let i=0;i<7;i++){
			let s = document.createElement("span");
			s.setAttribute("id",getKeyByValue(pecasED,pecasComputador[i]));
			s.setAttribute("class","peçaComp");
			c.appendChild(s);
			s.appendChild(document.createTextNode("\uD83C\uDC30"));
		};
		gameManager.decideQuemComeca();
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
			e=gameManager.valorEsquerdoPeca(i);
			d=gameManager.valorDireitoPeca(i);
			x =e+d;
			valoresJog.push(x);
		};
		for (let i of pecasComputador){
			e=gameManager.valorEsquerdoPeca(i);
			d=gameManager.valorDireitoPeca(i);
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
			gameManager.gerarTexto("Jogador comeca");
			gameManager.jogo("computador");
		}else if (a<indexComp.length){
			gameManager.gerarTexto("Computador comeca");
			gameManager.jogo("jogador");
		};
	},
	jogo:function(jogou){
		let tI = gameManager.valorInicioTab();
		let tF = gameManager.valorFimTab();
		if(pecasTabuleiro.length>1){
			if(pecasJogador.length==0){
				gameManager.gerarTexto("Jogador ganhou!!");
				gameManager.classificaçoes("jogador");
			}else if (pecasComputador.length==0){
				console.log("aa");
				gameManager.gerarTexto("Computador ganhou!!");
				gameManager.classificaçoes("computador");
			}else if ((tI===tF) && (gameManager.pecaApareceVezes(tI)==true)){
				console.log(gameManager.empateCalculeVencedor());
				gameManager.classificaçoes(gameManager.empateCalculeVencedor());
			}else if (gameManager.ninguemPodeJogarMais()==true){
				console.log("empate");
				gameManager.classificaçoes(gameManager.empateCalculeVencedor());
			}else{
				if(jogou=="jogador"){
					aJogar="computador";
					setTimeout(gameManager.compJoga, 2000);
				}else if (jogou=="computador"){
					aJogar="jogador";
					gameManager.jogJoga();
				};
			}
		}else{
			if(jogou=="jogador"){
				aJogar="computador";
				setTimeout(gameManager.compJoga, 2000);
			}else if (jogou=="computador"){
				aJogar="jogador";
				gameManager.jogJoga();
			};
		};
	},
	calculePontosJog:function(){
		let j=0;
		for (i of pecasJogador){
			let e = gameManager.valorEsquerdoPeca(i);
			let d = gameManager.valorDireitoPeca(i);
			j=j+e+d;
		};
		return j;
	},
	calculePontosComp:function(){
		let c=0;
		for (i of pecasComputador){
			let e = gameManager.valorEsquerdoPeca(i);
			let d = gameManager.valorDireitoPeca(i);
			c=c+e+d;
		};
		return c;
	},
	empateCalculeVencedor:function(){
		let j=gameManager.calculePontosJog();
		let c=gameManager.calculePontosComp();
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
			let tI = gameManager.valorInicioTab();
			let tF = gameManager.valorFimTab();
			for (i of pecasComputador){
				lista.push(gameManager.valorEsquerdoPeca(i));
				lista.push(gameManager.valorDireitoPeca(i));
			};
			for (i of pecasJogador){
				lista.push(gameManager.valorEsquerdoPeca(i));
				lista.push(gameManager.valorDireitoPeca(i));
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
			let e = gameManager.valorEsquerdoPeca(j);
			let d = gameManager.valorDireitoPeca(j);
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




	// Funcoes para Pecas do Jogador
	jogJoga:function(){
		gameManager.checkabilityMonte();
		gameManager.ableJogador();
		gameManager.checkAbilityPassarJog();
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
		let tI = gameManager.valorInicioTab();
		let tF = gameManager.valorFimTab();
		let numeros=[];
		for (i of pecasJogador){
			numeros.push(gameManager.valorDireitoPeca(i));
			numeros.push(gameManager.valorEsquerdoPeca(i));
		};
		if (tI=="free"){
			gameManager.disableMonte();
		}else if ((numeros.includes(tI)===false) && (numeros.includes(tF)===false)){
			document.getElementById("caixa2").style.pointerEvents = "auto";
		}else{
			gameManager.disableMonte();
		};
	},
	verificarJogada:function(a){
		gameManager.verificaPeca(a);
		let t= a.innerText;
		let e = gameManager.valorEsquerdoPeca(t);
		let d = gameManager.valorDireitoPeca(t);
		let tI = gameManager.valorInicioTab();
		let tF = gameManager.valorFimTab();
		if (tI=="free"){
			gameManager.toTabuleiroDireito(a);
		}else if (e==tF && d==tI){
			document.getElementById("escolhas").style.display="block";
			esqdirAjuda=a;
		}else if (d==tI){
			gameManager.toTabuleiroEsquerdo(a);
		} else if (e==tF){
			gameManager.toTabuleiroDireito(a);
		};
	},
	verificaPeca:function(a){
		let t = a.innerText;
		let tI = gameManager.valorInicioTab();
		let tF = gameManager.valorFimTab();
		let e = gameManager.valorEsquerdoPeca(t);
		let d = gameManager.valorDireitoPeca(t);
		if (e==tI || d==tF){
			gameManager.gerarTexto("Tens de rodar a peca para poder jogar");
		}else if (e!=tF && d!=tI){
			gameManager.gerarTexto("Nao podes jogar esta peca!");
		};
		
	},
	escolherladoEsquerdo:function(){
		gameManager.escolherlado("esquerdo");
	},
	escolherladoDireito:function(){
		gameManager.escolherlado("direito");
	},
	escolherlado:function(lado){
		let a = esqdirAjuda;
		if (lado=="direito"){
			gameManager.toTabuleiroDireito(a);
		}else if (lado=="esquerdo"){
			gameManager.toTabuleiroEsquerdo(a);
		};
		document.getElementById("escolhas").style.display="none";
	},
	toTabuleiroEsquerdo:function(a){
		let t = document.getElementById("jogo");
		let s = document.createElement("span");
		let c= a.innerText;
		let key = getKeyByValue(pecasTodas,c);
		let d = document.getElementById(key);
		d.remove();
		s.setAttribute("id",getKeyByValue(pecasTodas,c));
		//s.setAttribute("class","peçaTabuleiro");
		t.insertBefore(s, t.firstChild);
		s.appendChild(document.createTextNode(c));
		let i = pecasJogador.indexOf(c);
		pecasJogador.splice(i,1);
		pecasTabuleiro.unshift(c);
		gameManager.disableJogador();
		gameManager.disableMonte();
		gameManager.jogo("jogador");
		gameManager. gerarTexto("Computador esta a jogar");
	},
	toTabuleiroDireito:function(a){
		let t = document.getElementById("jogo");
		let s = document.createElement("span");
		let c= a.innerText;
		let key = getKeyByValue(pecasTodas,c);
		let d = document.getElementById(key);
		d.remove();
		s.setAttribute("id",getKeyByValue(pecasTodas,c));
		t.appendChild(s);
		s.appendChild(document.createTextNode(c));
		let i = pecasJogador.indexOf(c);
		pecasJogador.splice(i,1);
		pecasTabuleiro.push(c);
		gameManager.disableJogador();
		gameManager.disableMonte();
		gameManager.jogo("jogador");
		gameManager. gerarTexto("Computador esta a jogar");
	},
	pecaMonteToJogador:function(){
		let j = document.getElementById("maoJogador");
		let s = document.createElement("span");
		let l = pecasMonte.length;
		let x = Math.floor(Math.random()*(l))
		let c = pecasMonte[x];
		pecasMonte.splice(x,1);
		pecasJogador.push(c);
		let d = document.getElementById(l+99);
		d.remove();
		s.setAttribute("id",getKeyByValue(pecasED,c));
		s.setAttribute("class","peçaJog");
		s.appendChild(document.createTextNode(c));
		s.setAttribute("onclick","gameManager.verificarJogada(this);");
		s.setAttribute("onwheel","gameManager.rodarPeca(this);");
		j.append(s);
		gameManager.checkabilityMonte();
		gameManager.checkAbilityPassarJog();
	},
	rodarPeca:function(a){
		let t= a.innerText;
		let e = gameManager.valorEsquerdoPeca(t).toString();
		let d = gameManager.valorDireitoPeca(t).toString();
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
		let tI = gameManager.valorInicioTab();
		let tF = gameManager.valorFimTab();
		let numeros=[];
		if (pecasMonte.length==0){
			for (i of pecasJogador){
			numeros.push(gameManager.valorDireitoPeca(i));
			numeros.push(gameManager.valorEsquerdoPeca(i));
			};
			if ((numeros.includes(tI)===false) && (numeros.includes(tF)===false)){
				let e = document.getElementById("passaJogada");
				e.disabled=false;
			};
		};
	},
	passarJogadaJog: function(){
		gameManager.jogo("jogador");
		gameManager. gerarTexto("Computador esta a jogar");
		gameManager.passarJogadaOff();
		
	},

	passarJogadaOff: function(){
		let e = document.getElementById("passaJogada");
		e.disabled=true;
	},




	
	
	// Funcoes para Pecas do Computador
	compJoga:function(){
		console.log("pecascomp   ");
		for (i of pecasComputador){
			console.log(getKeyByValue(pecasTodas,i));
		};
		let tI = gameManager.valorInicioTab();
		let tF = gameManager.valorFimTab();
		let pecasPossiveis=[];
		if (tI=="free"){
			if (difficuldade=="dificil"){
				let key=gameManager.difficuldadeDificil(pecasComputador);
				gameManager.compToTabuleiroDireito(key);
			}else if (difficuldade=="facil"){
				let key=gameManager.difficuldadeFacil(pecasComputador);
				gameManager.compToTabuleiroDireito(key);
			};
		}else {
			for (i of pecasComputador){
				let e=gameManager.valorEsquerdoPeca(i);
				let d=gameManager.valorDireitoPeca(i);
				if (e==tF || d==tI){
					pecasPossiveis.push(i);
				} else if(e==tI || d==tF){
					pecasPossiveis.push(i);	
				};
			};
			if ((pecasPossiveis.length==[])&&(pecasMonte.length==0)){
				gameManager.jogo("computador");
				gameManager.gerarTexto("É a tua vez");
			} else if ((pecasPossiveis.length==[])&&(pecasMonte.length>0)){
				gameManager.pecaMonteToComp();
				gameManager.compJoga();
			}else{
				if (difficuldade=="dificil"){
					let k=gameManager.difficuldadeDificil(pecasPossiveis);
					gameManager.computadorPecaVerifica(k);
				}else if (difficuldade=="facil"){
					let k=gameManager.difficuldadeFacil(pecasPossiveis);
					gameManager.computadorPecaVerifica(k);
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
		s.setAttribute("id",getKeyByValue(pecasED,c));
		s.setAttribute("class","peçaComp");
		s.appendChild(document.createTextNode("\uD83C\uDC30"));
		j.append(s);
	},
	computadorPecaVerifica:function(key){
		let a=pecasTodas[key];
		let tI = gameManager.valorInicioTab();
		let tF = gameManager.valorFimTab();
		let e=gameManager.valorEsquerdoPeca(a);
		let d=gameManager.valorDireitoPeca(a);
		if (d==tI){
			gameManager.compToTabuleiroEsquerdo(key);
		} else if (e==tF){
			gameManager.compToTabuleiroDireito(key);
		} else if(e==tI){
			e=e.toString();
			d=d.toString();
			let keynovo=d+e;
			let pecaInv=pecasTodas[keynovo];
			document.getElementById(key).id=keynovo;
			let i =pecasComputador.indexOf(a);
			pecasComputador[i]=pecaInv;
			gameManager.compToTabuleiroEsquerdo(keynovo);
		} else if(d==tF){
			e=e.toString();
			d=d.toString();
			let keynovo=d+e;
			let pecaInv=pecasTodas[keynovo];
			let element=document.getElementById(key);
			element.id=keynovo;
			let i =pecasComputador.indexOf(a);
			pecasComputador[i]=pecaInv;
			gameManager.compToTabuleiroDireito(keynovo);
		};
	},
	compToTabuleiroDireito:function(key){
		console.log(key);
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
		gameManager.jogo("computador");
		gameManager.gerarTexto("É a tua vez");
	},
	compToTabuleiroEsquerdo:function(key){
		console.log(key);
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
		gameManager.jogo("computador");
		gameManager.gerarTexto("É a tua vez");
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
			key = getKeyByValue(pecasTodas,lll[0]);
			return(key)
		}else{
			for (i of lista){
				let e=gameManager.valorEsquerdoPeca(i);
				let d=gameManager.valorDireitoPeca(i);
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
			key = getKeyByValue(pecasTodas,lll[y]);
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
			key = getKeyByValue(pecasTodas,lll[0]);
			return(key)
		}else{
			for (i of lista){
				let e=gameManager.valorEsquerdoPeca(i);
				let d=gameManager.valorDireitoPeca(i);
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
			key = getKeyByValue(pecasTodas,lll[y]);
			return (key);
		};
	},



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
			let i=getKeyByValue(pecasTodas,pecasTabuleiro[0]);
			return(gameManager.valorKeyEsquerdo(i));
		};
	},
	valorFimTab:function(){
		let x=pecasTabuleiro.length;
		if (x==0){
			return "free"
		} else{
			let i=getKeyByValue(pecasTodas,pecasTabuleiro[pecasTabuleiro.length-1]);
			return(gameManager.valorKeyDireito(i));
		};
	},
	valorEsquerdoPeca:function(x){
		let i=getKeyByValue(pecasTodas,x);
		return(gameManager.valorKeyEsquerdo(i));
	},
	valorDireitoPeca:function(x){
		let i=getKeyByValue(pecasTodas,x);
		return(gameManager.valorKeyDireito(i));
	},
	pecaInversa:function(peca){
		let e=gameManager.valorEsquerdoPeca(peca).toString();
		let d=gameManager.valorDireitoPeca(peca).toString();
		let key=d+e;
		let inversa=pecasTodas[key];
		return (inversa);
	},

	certezaDomino:function(){
		document.querySelector(".menuSec").style.display="none";
		document.querySelector("#certeza").style.display="block";
		document.querySelector("#certeza").style.opacity=1;
	},

	desistirJogo: function(){
		gameManager.resetDomino();
		document.querySelector(".menuSec").style.display="none";
		document.querySelector("#principal").style.display="none";
		document.querySelector("#principal").style.opacity= 1;
		document.querySelector("#menu-1").style.display="block";
		document.querySelector("#certeza").style.display="none";
	},

	resetDomino: function(){ 
		for(let i=100; i<pecasMonte.length+100;i++){
			let l =document.getElementById(i);
			l.remove();
		};
		for(let i=0; i<pecasJogador.length;i++){
			let key = getKeyByValue(pecasTodas,pecasJogador[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		for(let i=0; i<pecasComputador.length;i++){
			let key = getKeyByValue(pecasTodas,pecasComputador[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		for(let i=0; i<pecasTabuleiro.length;i++){
			let key = getKeyByValue(pecasTodas,pecasTabuleiro[i]);
			let l =document.getElementById(key);
			l.remove();
		};
		pecasMonte=[];
		pecasJogador=[];
		pecasComputador=[];
		pecasTabuleiro=[];
		aJogar="";
		gameManager.gerarTexto("");
		difficuldade="";
	},

	novoJogo:function(){
		//quero que esta função, quando for ativada possa limpar o tabuleiro, as mãos dos jogadores e voltar ao menu das dificuldades para que possa ser escolhido novamente o modo de jogo pretendido
		gameManager.resetDomino();
		document.querySelector(".menuSec").style.display="none";
		document.querySelector("#classificacao").style.display="none";
		gameManager.mostrarDificuldades();
	},

	gerarTexto: function(txt){
		document.getElementById("texto").innerText=txt;	
	},
	classificaçoes: function(player){
		//gameManager.mostrarTabela();
		gameManager.addJogotoTabela(gameManager.calculePontosComp(),gameManager.calculePontosJog());
		document.getElementById("principal").style.opacity=0.2;
		document.querySelector("#caixa2").style.pointerEvents="none";
		document.querySelector("#menu-2").style.pointerEvents ="none";
		document.getElementById("classificacao").style.display="block";	
		if(player=="jogador"){
			document.getElementById("ganhar").innerText="Jogador!!! Parabens!!!!";
		}else if(player=="computador"){
			document.getElementById("ganhar").innerText="Computador.";
		};
		document.getElementById("pontos").innerText="Jogador tem "+gameManager.calculePontosJog()+" pontos.Computador tem "+gameManager.calculePontosComp()+" pontos";
	},

	addJogotoTabela: function(comp,jog){
		if(difficuldade=="dificil"){
			let t =document.querySelector("#tabDificil");
		let l = document.querySelector("#tabDificil").rows.length;

		let row = t.insertRow(l);
		let c1=row.insertCell(0);
		let c2=row.insertCell(1);
		let c3=row.insertCell(2);
		c1.innerHTML="Jogo "+ l;
		c2.innerHTML = comp;
		c3.innerHTML = jog;

		let jogos=[];

		jogos.push("Jogo"+l,comp,jog);

		statD.push(jogos);

		let list = gameManager.totalJogadoresD();
		let cComp=list[0];
		let cJog= list[1];
		document.getElementById("CompTotalD").innerText=cComp;
		document.getElementById("JogTotalD").innerText=cJog;
		} else if (difficuldade="facil"){
			let t =document.querySelector("#tabNormal");
			let l = document.querySelector("#tabNormal").rows.length;
	
			let row = t.insertRow(l);
			let c1=row.insertCell(0);
			let c2=row.insertCell(1);
			let c3=row.insertCell(2);
			c1.innerHTML="Jogo "+ l;
			c2.innerHTML = comp;
			c3.innerHTML = jog;
	
			let jogos=[];
	
			jogos.push("Jogo"+l,comp,jog);
	
			statN.push(jogos);
			
			let list = gameManager.totalJogadoresN();
			let cComp=list[0];
			let cJog= list[1];
	
			document.getElementById("CompTotalN").innerText=cComp;
			document.getElementById("JogTotalN").innerText=cJog;
		}
	},

	mostrarTabela: function(){
		let t= document.querySelector(".tabela");
		for(i=0; i<stat.length;i++){
			let row = t.insertRow(i+1);
			for(j=0;j<i.length;j++){
				if(j==0){
					let c1=row.insertCell(0);
					c1.innerHTML=stat[i[j]];
				} else if(j==1){
					let c2=row.insertCell(1);
					c2.innerHTML=stat[i[j]];
				}else if(j==2){
					let c3=row.insertCell(2);
					c3.innerHTML=stat[i[j]];
				}
			}
		}
	},

	totalJogadoresD: function(){
		let cComp=0;
		let cJog=0;
		let l =[];
		
		for(i of statD){
			cComp+=i[1];
			cJog+=i[2];
		}
		l.push(cComp, cJog);
		return l;
	},

	totalJogadoresN: function(){
		let cComp=0;
		let cJog=0;
		let l =[];
		
		for(i of statN){
			cComp+=i[1];
			cJog+=i[2];
		}
		l.push(cComp, cJog);
		return l;
	}

};
function getKeyByValue(object, value) {
  return Object.keys(object).find(key => object[key] === value);
};