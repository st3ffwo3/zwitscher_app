var baseURL = "http://23.23.183.141/";
var feedURL = "json/feed";
var fbGraphURL = "https://graph.facebook.com/";

$(document).ready(function() {

	

	$("#q").change(function() {
		$(this).val($.trim($(this).val()));
	});
	
	$("#search").click(function() {
		var q = $("#q").val();
		var count = $("#slider").val();
		var untilDate = $("#date").val();
		if(!q) {
			displayMessage("Fehler", "Das Eingabefeld ist leer.<br/>Bitte geben Sie einen Suchbegriff ein.");
		} else {
			clearMessage();
			var url = baseURL + feedURL + '?keyword=' + q;
			if(untilDate) {
				url += '&untilDate=' + untilDate;
			}
			if(count){
				url += '&count=' + count;
			}
			$('#results').empty();
			
			$.mobile.showPageLoadingMsg("b", "Lade Ergebnisse...");
			$("#result_container").hide();
			$.getJSON(url, function(data) {
				if(data.length != 0) {
					var results = $("#results");
					$("#result_container").slideDown();
					data.sort(function(a,b) {
						return b.creationDate - a.creationDate;
					});
					$.each(data, function(i, item) {
						results.append(renderItem(item));
					});
					results.listview('refresh');
					registerExpander();
				} else {
					displayMessage("Keine Ergebnisse", "Unter dem Begriff '<strong>" + $("#q").val() + "</strong>' konnte leider nichts gefunden werden.<br/>Bitte versuchen Sie es mit einem anderen Begriff.");
					$("#slider").val("25");
					$("#date").val("");
				}
			})
			.error(function() { displayMessage("Fehler", "Fehler bei der Abfrage! <br/> Bitte versuchen Sie es erneut."); $.mobile.hidePageLoadingMsg(); })
			.complete(function() { $.mobile.hidePageLoadingMsg(); });
			
		}
	});
	
	
	$("#reset").click(function() {
		$("#slider").val("25");
		$("#slider").slider("refresh");
		$("#date").val("");
	});
	
	$("#new_message").submit(function() {
		alert('Handler for .submit() called.');
		return false;
	});
	
	
});

function registerExpander() {

	$("#results li").hover(
		function () {
			var el = $(this).find(".msg");
			var finish = {width: this.style.width, height: this.style.height};
			var cur = {width: el.width()+'px', height: el.height()+'px'};		
			el.stop(true, true).css("white-space", "normal");
			var next = {width: el.width()+'px', height: el.height()+'px'};
			
			el.css(cur) // restore initial dimensions
            .animate(next, 500, function()  // animate to final dimensions
            {
               el.css(finish); // restore initial style settings
            });
		},
		function () {
			var el = $(this).find(".msg");
			var finish = {width: this.style.width, height: this.style.height};
			var cur = {width: el.width()+'px', height: el.height()+'px'};		
			el.stop(true, true).css("white-space", "nowrap");
			var next = {width: el.width()+'px', height: el.height()+'px'};
			el.css("white-space", "normal");
			el.css(cur) // restore initial dimensions
            .animate(next, 500, function()  // animate to final dimensions
            {
               el.css(finish); // restore initial style settings
			   el.css("white-space", "nowrap");
            });
		}
	);

}

function displayMessage(title, text) {
	var message = $( '<div>' , {
		'id' : 'message',
		'class': "ui-footer ui-bar-e",
		'hidden' : true,
		style: "overflow: auto; padding:10px 15px;",
	});

	message.append( "<h3>" + title + "</h3>" ).append( "<p>" + text + "</p>" );
	
	$("#message").replaceWith(message);
	$("#message").fadeIn();
}

function clearMessage() {
	$("#message").hide();
}


/* TEMPLATES */
function renderItem(data) {
	var html;
	
	if(data.link) {
		html = renderLink(data);
	} else {
		html = $('<div>');
	}
	if(data.objectType && data.objectType === "photo") {
		html.append(renderPhoto(data));
	}
	if(data.author) {
		html.append(renderAuthor(data));
	}
	if(data.message) {
		html.append(renderMessage(data));
	}
	if(data.creationDate) {
		html.append(renderDate(data));
	}
	if(data.source) {
		html.append(renderSource(data));
	}

	return $('<li>').append(html);
	
}


function renderMessage(data) {
	return $('<p />', {'class' : 'msg'}).append(data.message);
}

function renderPhoto (data) {
	var id = getParameterByName(data.link, "fbid");
	var url;
	$.getJSON(fbGraphURL + id, function(json) {
		if(json.length != 0) {
			url = json.images[5].source;
			$("img[fbid=" + json.id + "]").attr("src", url);
		}
	})
	.error(function() { })
	.complete(function() { });


	return $('<img />', {'class' : 'photo', 'fbid' : id, 'src' : 'css/images/img-loader.gif' });
}

function renderLink (data) {
	return $('<a />', {'class' : 'link', 'href' : data.link});
}

function renderAuthor (data) {
	return $('<h3 />', {'class' : 'author'}).append(data.author);
}

function renderDate (data) {
	var date = new Date(data.creationDate);
	
	return $('<p />', {'class' : 'ui-li-aside', 'style' : 'font-weight: bold;'}).append(date.getDate() + "." + (date.getMonth()+1) + "." + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes());
}

function renderSource (data) {
	return $('<div />', {'class' : data.source.toLowerCase() + " source"});
}


/* MISC */
function getParameterByName(url, name)
{
  name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
  var regexS = "[\\?&]" + name + "=([^&#]*)";
  var regex = new RegExp(regexS);
  var results = regex.exec(url);
  if(results == null)
    return "";
  else
    return decodeURIComponent(results[1].replace(/\+/g, " "));
}


