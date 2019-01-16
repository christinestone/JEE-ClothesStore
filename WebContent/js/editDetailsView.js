var EditDetailsView = Backbone.View.extend({
	model: ClothesStore,
	id: "addContainer",
	initialize: function(){
		console.log("init EditDetailsView");
	},
	events:{
		"click #btnSave": "saveItem",
		"click #btnDelete": "deleteItem",
		"click #btnAdd": "add",
	},
	saveItem: function(e){
		if(($('#id2').val())==''){
			this.model.save({
				id:parseInt($('#id2').val()),
				product:$('#product2').val(),
				brand:$('#brand2').val(),
				size:$('#size2').val(),
				price:$('#price2').val(),
				colour:$('#colour2').val(),
				fabric:$('#fabric2').val(),
				origin:$('#origin2').val(),
				description:$('#description2').val(),
				image:$('#image2').val()
			},{
				success:function(item){
					dataTableBuild();
					alert("sucess - new item added");
				} 
			});
		}else{
			this.model.save({
				id:parseInt($('#id2').val()),
				product:$('#product2').val(),
				brand:$('#brand2').val(),
				size:$('#size2').val(),
				price:$('#price2').val(),
				colour:$('#colour2').val(),
				fabric:$('#fabric2').val(),
				origin:$('#origin2').val(),
				description:$('#description2').val(),
				image:$('#image2').val()
			},{
				success:function(item){
					dataTableBuild();
					alert("sucess - item updated");
				} 
			});
		}
		return false;
	},

	deleteItem: function(e){
		var productID = parseInt($('#id2').val());
		this.model.set({id:productID});
		this.model.destroy(
				{
					success:function(data){
						dataTableBuild();
						alert("item deleted");
					}
				});
		return false;
	},

	add: function(e){
		$('#btnDelete').hide();
		alert('Add');
		id:$('#id2').val(null);
		product:$('#product2').val("");
		brand:$('#brand2').val("");
		size:$('#size2').val("");
		price:$('#price2').val("");
		colour:$('#colour2').val("");
		fabric:$('#fabric2').val("");
		origin:$('#origin2').val("");
		description:$('#description2').val("");
		image:$('#image2').val("");
		return false;
	},



	render: function(){
		console.log("render test"+this.model.get('id'));
		var template= _.template($('#edit-product-details').html())( this.model.toJSON());
		return this.$el.html(template);
	}
});