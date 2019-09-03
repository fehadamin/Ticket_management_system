<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>upload</title>
</head>
<body>

	<form action="upload_file_store.htm" method="post" enctype="multipart/form-data">
		<div class="box">
			<h1>UPLOAD</h1>
			<div class="form-group" style="display:none;">
				<label> File Name: <span class="alert"></span></label> <input
					type="text" class="form-control" value="abc" name="filename"
					placeholder="Enter file key">
			</div>
  
  			
  			
			<div class="form-group">
				<label> File Name: <span class="alert"></span></label> <input
					type="file" class="form-control" name="upload" accept=".xls,.xlsx">
			</div>
			<button type="submit">submit</button>
			</div>
	</form>



</body>
</html>