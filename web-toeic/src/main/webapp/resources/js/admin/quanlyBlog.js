


$(document).ready(function() {
	var simplemde;

	//default. load all object blog
	window.onload = function () {
		loadAllBlog();

		// creat markdown
		simplemde =new SimpleMDE({
			element: document.getElementById("markdown-editor"),
			spellChecker: false,
		});

	};




	function loadAllBlog(){
		$.ajax({
			dataType : 'json',
			type:'GET',
			url:"http://localhost:8080/webtoeic/api/admin/blog/loadBlog",

			success: function(data){

				//lấy từng phần tử ra
				var fields,id,hinhanh,tieude;
				var trHTML ="";
				for(var i = 0; i <data.length; i++ ){

					fields = data[i].split(','); //=> ["Id:1","Tieude:tên gì","Hinhanh:123.png"]

					id = fields[0].split(':'); //=> ["id","1"]

					hinhanh = fields[1].split(':'); //=> ["Tieude","tên gì"]

					tieude = fields[2].split(':'); //=> ["Hinhanh","123.png"]


					trHTML += '<tr><td class= "center">' + id[1] + '</td>'
						+'<td class= "center">' + tieude[1] + '</td>'

						+'<td class= "center">' + hinhanh[1] + '</td>'

						+'<td class = "center"> <a id="edit.'+ id[1] +' "'

						+'class="yellow editBlog"><button class="btn btn-warning">Cập nhật</button></a> '

						+' <a id="delete.'+ id[1] +' "'

						+'class="red deleteBlog" ><button class="btn btn-danger">Xóa</button></a> </td>'

						+'</tr>';
				}


				$('tbody').html(trHTML);

			}, error : function(e) {
				alert("error");
				console.log("ERROR: ", e);
			}
		});


	}


	//add new blog

	$('#btnAddNewBlog').click(function() {
		var formData = new FormData();

		var file_hinhanh = $('#hinhanhbl')[0].files[0];
		var tieude = $('#tieudebl').val();
		var mota = $('#mota').val();
		var contentMarkdown =  simplemde.value(); //get from textarea markdown
		var contentHTML = simplemde.options.previewRender(contentMarkdown);


		formData.append("file_hinhanh",file_hinhanh);
		formData.append("tieude",tieude);
		formData.append("mota",mota);
		formData.append("contentMarkdown",contentMarkdown);
		formData.append("contentHtml",contentHTML);

		$.ajax({
			data: formData,
			type:'POST',
			url:"http://localhost:8080/webtoeic/api/admin/blog/save",
			enctype : 'multipart/form-data',
			contentType : false,
			cache : false,
			processData : false,
			success: function(data){

				$('#grammarModal').modal('hide');
				loadAllBlog();
				$('#info-success').text("Thêm mới bài blog thành công");

			},

			error : function(e) {
				alert("error");
				$('#grammarModal').modal('hide');
				console.log("ERROR: ", e);
			}

		});
	});

// delete blog
	$(document).on('click','.deleteBlog',function(){
		var deleteId = $(this).attr('id');
		var fields = deleteId.split('.');
		var idBlog = fields[1];

		if(confirm("Bạn muốn xóa bài blog này?"))
		{
			$.ajax({
				type:'POST',
				url:"http://localhost:8080/webtoeic/api/admin/blog/delete/"+idBlog,
				success: function(data){
					loadAllBlog();
					$('#info-success').text("Xóa bài blog thành công");
				},
				error : function(e) {
					alert("error");
					console.log("ERROR: ", e);
				}

			});
		}

	});

	//edit baiGrammar	
	$(document).on('click','.editBlog',function(event){
		var editId = $(this).attr('id');
		var fields = editId.split('.');
		var idBlog = fields[1];

		$.ajax({
			type:'GET',
			url:"http://localhost:8080/webtoeic/api/admin/blog/infoBlog/"+idBlog,
			success: function(data){

				var jsonObject = new Object();
				fields = data.split('|');

				tieude = fields[0].split('==');
				jsonObject.tieude = tieude[1];

				hinhanh = fields[1].split('==');
				jsonObject.hinhanh = hinhanh[1];

				mota = fields[2].split('==');
				jsonObject.mota = mota[1];

				noidung = fields[3].split('==');
				jsonObject.noidung = noidung[1];

				//set data for modal

				var modal = $('#grammarModal');
				$('#grammarModal #idGrammarModal').val(idBlog);
				modal.find('.modal-body #tieudebl').val(jsonObject.tieude);
				modal.find('.modal-body #mota').val(jsonObject.mota);
				modal.find('.modal-header #titleModal').text("Cập nhật blog");
				simplemde.value(jsonObject.noidung);


				//simplemde = null;
				$('#btnUpdate').show();
				$('#btnAddNewBlog').hide();
				$('#grammarModal').modal('show');


			},

			error : function(e) {
				alert("error");
				console.log("ERROR: ", e);
			}

		});




		$('#btnUpdate').click(function() {

			var formData = new FormData();

			var tieude = $('#tieudebl').val();
			var mota = $('#mota').val();
			var contentMarkdown =  simplemde.value(); //get from textarea markdown
			var contentHTML = simplemde.options.previewRender(contentMarkdown);


			var file_hinhanh = $('#hinhanhbl')[0].files[0];

			if (file_hinhanh) {
				formData.append("file_hinhanh", file_hinhanh);
			} else {
				formData.append("file_hinhanh", "undefined");
			}



			formData.append("idBlog",idBlog);
			formData.append("tieude",tieude);
			formData.append("mota",mota);
			formData.append("contentMarkdown",contentMarkdown);
			formData.append("contentHtml",contentHTML);


			$.ajax({
				data: formData,
				type:'POST',
				url:"http://localhost:8080/webtoeic/api/admin/blog/update",
				enctype : 'multipart/form-data',
				contentType : false,
				cache : false,
				processData : false,

				success: function(data){
					$('#grammarModal').modal('hide');
					$('#info-success').text("Cập nhật bog thành công");
					loadAllBlog();

				},

				error : function(e) {
					alert("error");
					console.log("ERROR: ", e);
				}

			});

		});



	});



});
