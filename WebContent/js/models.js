
var rootURL = "./rest/clothes";
var ClothesStore = Backbone.Model.extend({
	urlRoot:rootURL,
	defaults:{       
		"id":"", 
		"product":"",     
		"brand":"",
		"size":"",
		"price":"",
		"colour":"",
		"fabric":"",
		"origin":"", 
		"description":"",
		"image":""    },
  initialize: function(){
    console.log("clothes init");
    this.on('change', function(){
    	console.log('Values for a model have changed');
    });
  }
});
