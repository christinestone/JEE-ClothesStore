var DetailsView = Backbone.View.extend({
	model: ClothesStore,
	initialize: function(){
		console.log("init DetailsView");
	},

	render: function(){
		console.log("render test"+this.model.get('id'));
		var template= _.template($('#product-details').html())( this.model.toJSON());
		return this.$el.html(template);
	}
});