
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>
	<form id='fForm' class="form-actions form-horizontal"
		action="fileUpload?kind=uploadVmTemplate&un=admin@internal&pwd=adminPassword&ex=export"
		encType="multipart/form-data" method="post">
		<div class="control-group">
			<label class="control-label">上传文件:</label>
			<div class="controls">
				<input type="file" name="file" style="width: 550">

			</div>
			<div class="controls">
				<input type="file" name="file" style="width: 550">
			</div>
			<div class="controls">
				<input type="file" name="file" style="width: 550">
			</div>
			<label class="control-label">上传进度:</label>
			<div class="controls">
				<div class="progress progress-success progress-striped"
					style="width: 50%">
					<div id='proBar' class="bar" style="width: 0%"></div>
				</div>
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="button" id="subbut" class="btn">submit</button>
			</div>
		</div>
	</form>
	<iframe name="uploadf" style="display: none"></iframe>
</body>
</html>
<script>
	$(document).ready(function() {
		$('#subbut').bind('click', function() {
			$('#fForm').submit();
			var eventFun = function() {
				$.ajax({
					type : 'GET',
					url : 'fileUploadStatus',
					data : {},
					dataType : 'text',
					success : function(data) {
						$('#proBar').css('width', data);
						$('#proBar').empty();
						$('#proBar').append(data);
						if (data == "100%") {
							window.clearInterval(intId);
						}
					}
				});
			};
			var intId = window.setInterval(eventFun, 1);
		});
	});
</script>
