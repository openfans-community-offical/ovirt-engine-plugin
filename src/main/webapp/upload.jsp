<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
  <script src="resources/js/jquery.js"></script>
 </head>
 <body>
  <form method="post" action="fileUpload?kind=uploadVmTemplate&un=admin@internal&pwd=adminPassword&ex=export" enctype="multipart/form-data">
   <input type="file" name="file" />
   <input type="submit" />
  </form>
 </body>
</html>