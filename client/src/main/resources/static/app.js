var stompClient = null;
var uid = randString(32);
var gid = null;
var gamestate = null;
var player = "X";

function connect() {
	var socket = new SockJS('/ttt-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/ttt/gamestate/' + gid, function (data) {
			updateGamestate(JSON.parse(data.body));
		});
	});
}

function sendMove(x, y) {
	stompClient.send("/ttt/move/" + gid, {}, JSON.stringify({'player': uid, 'x': x, 'y': y}));
}

function refresh() {
	$.getJSON("/ttt/games/", function(data){
		$("#games").empty();
				
		if (data.length > 0) {
			for (var game in data) {
				game = data[game];
				$("#games").append("<tr><td>" + game.name  + "</td></tr>");
			}
		}
		else {
			$("#games").append("<tr><td>There are no games currently available. Try creating your own!</td></tr>");
		}
	});
}

function create() {
	var name = $("#gamename").val() || undefined;
	
	$('#createGameModal').modal('hide');
	
	$.post({
		url: "/ttt/game",
		data: { 
			player: uid,
			name: name
		}
	}).done(function(data) {
		console.log("Created Game");
		
		player = "X";
		
		$("#menu").hide();
		$("#tictactoe").show();
		
		gid = data.id;
		connect();
		
		updateGamestate(data);
	});
}

function updateGamestate(data) {
	gamestate = data;
	
	gameStatus();
	drawBoard();
}

function cleanGamestate() {
	gamestate = null;
		
	for (var x = 0; x < 3; x++) {
		for (var y = 0; y < 3; y++) {
			$("#".concat(x).concat(y)).text("");
		}
	}
}

function drawBoard() {
	for (var x = 0; x < 3; x++) {
		for (var y = 0; y < 3; y++) {
			$("#".concat(x).concat(y)).text(gamestate.board[x][y]);
		}
	}
}

function gameStatus() {
	var status = "";
	
	
	if (gamestate.started && !gamestate.winner && !gamestate.draw) {
		status = "'" + gamestate.startingPlayer + " goes first.";
	}
	else if (gamestate.winner) {
	    status = (gamestate.isX? "O" : "X") + " win.";
	}
	else if (gamestate.draw) {
		status = "It's a draw!"
	}
	else {
		status = "";
	}
	
	$("#status").text(status);
}

function randString(length) {
	var text = "";
	var alphanum = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	for (var i = 0; i < length; i++) {
		text += alphanum.charAt(Math.floor(Math.random() * alphanum.length));
	}

	return text;
}

$(function () {
	$("form").on('submit', function (e) {
		e.preventDefault();
	});
	
	$("#tictactoe").hide();
	
	$("#refresh").click(function() { refresh(); });
	$("#create").click(function() { create(); });

	$("#games").on( "click", "button", function() {
		join( $(this).attr('id') );
	});
	
	$("#board").on( "click", "td", function() {
		sendMove($(this).attr('x'), $(this).attr('y'));
	});
	
	refresh();
});
