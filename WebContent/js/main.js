var currentProduct = {};
	
var findFeaturedDresses = function(){
	$.ajax({
		type:'GET',
		url: 'http://localhost:8080/ClothesStoreEJB/rest/clothes/search/Needle&Thread',
			dataType:"json",
			success:renderFeaturedPage
		});
}
	
var renderFeaturedPage = function(data){
	
	console.log('renderFeaturedPage()')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	
	$.each(list, function(index,item){
		$('#featuredRow').append('<div class="col-xs-6 col-md-3">'+
								'<a href="#" class="thumbnail">'+
									' <img src="images/'+item.image+'">'+
								'<h4>'+item.product+'</h4>'+
								'<p>'+item.price+'</p></a></div>');
	});
}
	

var showDetails=function(productID){
	var item= new ClothesStore({id:productID});
	item.fetch({
		success:function(item){
			$('#detailsModal').html(new DetailsView({model:item}).render());
			$('#modal').modal('show');
		}
	});
}

var showDialog=function(productID){
	var item= new ClothesStore({id:productID});
	item.fetch({
		success:function(item){
			$('#detailsModal2').html(new EditDetailsView({model:item}).render());
			$('#editModal').modal('show');
		}
	})
}

var dataTableBuild=function(){
	$('#productDetails').dataTable({
		"bFilter": true,
        "sPaginationType": "full_numbers",
        "aoColumns": [
            { "mData": "id" },
            { "mData": "product" },
            { "mData": "brand" },
            { "mData": "price" },
            { "mData": "colour" },
            { "mData": "size"},
            { "mData": "id",
            	"mRender": function(data, type, full){
            		return '<a href="#" id="moreInfo" data-identity="' + data + '">More Info</a>'
            		} 
            },
            { "mData": "id",
            	"mRender": function(data, type, full){
            	return '<a href="#" id="edit" data-identity="'+ data+'">Edit</a>'
            	}
            }
        ],
        "bProcessing": true,
        "bServerSide":true,
        "sAjaxSource": "./rest/clothes",
        "destroy":true
	});
}

$(document).ready( function() {	
	findFeaturedDresses();	
	dataTableBuild();

	$(document).on("click", '#moreInfo', function(){showDetails($(this).data('identity'));});
	$(document).on("click", "#edit", function(){showDialog($(this).data('identity'));});

	$('.carousel').carousel({
		interval: 4000,
		pause: 'none'
		});
} );
