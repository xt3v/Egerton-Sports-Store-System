<!doctype html>
<%
	boolean loggedIn = false;
	String userName = "";
	String userId = "";
	String userRole = "";
	if(request.getCookies() != null){
		for(Cookie c  : request.getCookies()){
			if(c.getName().equals("egersportsession")) loggedIn = true;
			if(c.getName().equals("egersportuserid")) userId = c.getValue();
			if(c.getName().equals("egersportusername"))userName = c.getValue();
			if(c.getName().equals("egersportuserrole"))userRole = c.getValue();
		}
	}
    if(userRole.equals("storekeeper") || userRole.equals("coordinator")){
	    response.sendRedirect("/adminLogin.jsp");
	}
	if(!loggedIn){
		  response.sendRedirect("/login");
	}

%>
<html>
	<head>
		<title>Sports Department</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="vendor/parsley/parsley.css">
		<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
		  <!-- Custom fonts for this template-->
		<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

		<!-- Page level plugin CSS-->
		<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
		<link rel="stylesheet" href="css/indexmain.css" />
		<link rel="stylesheet" href="css/sb-admin.min.css">
		 <link rel="stylesheet" href="vendor/select2/select2.min.css">
		<link rel="stylesheet" href="vendor/sweetalert/sweetalert2.min.css">
		
		</head>
	<body class="is-preload" style="overflow-x:hidden;">
		<!-- Wrapper -->
			

				<!-- Header -->
					<header id="header">
						<div class="container-fluid">
                             <div class="row">
                                  <div class="col-md-12 col-sm-12 col-xs-12 d-flex justify-content-center align-items-center">
									  <img class="symbol" src="./images/eger.png">
								  </div>
								  <div class="col-md-12 col-xs-12 col-sm-12">
									  <h3 class="text-center" style="margin-bottom:20px;">Sports Department</h3>
								  </div>
							 </div>
						</div>
					</header>

				<!-- Main -->
					<div id="main" class="container-fluid">
						<div class="row justify-content-center">
							<header class="col-md-12">
								<h5 class="text-center" style="margin-bottom:10px;" id="nameAndReg"> <%=userName+" "+userId %> </h5><br />
								<div class="col-12 d-flex justify-content-center">
								    <button class="btn btn-primary" id="logBtn">Log Out</button>
								</div>
							</header>
							<section class="tiles mt-1 col-md-11">
								<%
								  String coachCaptainOptions = "\t<article class=\"style3\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t<span class=\"image\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t<img src=\"images/pic01.jpg\" alt=\"\" />\n" +
										  "\t\t\t\t\t\t\t\t\t\t</span>\n" +
										  "\t\t\t\t\t\t\t\t\t\t<a onclick=\"requestGame()\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t<h2>Request Game</h2>\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t<div class=\"content\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t\t<p>Request for a game to be scheduled</p>\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
										  "\t\t\t\t\t\t\t\t\t\t</a>\n" +
										  "\t\t\t\t\t\t\t\t\t</article>"+
										  "\t<article class=\"style3\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t<span class=\"image\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t<img src=\"images/pic02.jpg\" alt=\"\" />\n" +
										  "\t\t\t\t\t\t\t\t\t\t</span>\n" +
										  "\t\t\t\t\t\t\t\t\t\t<a onclick=\"requestItems()\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t<h2>Request Items</h2>\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t<div class=\"content\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t\t<p>Request for Items  to be issued for a game</p>\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
										  "\t\t\t\t\t\t\t\t\t\t</a>\n" +
										  "\t\t\t\t\t\t\t\t\t</article>";

								%>
							    <%= userRole.equals("coach") ? coachCaptainOptions : userRole.equals("captain") ? coachCaptainOptions : "" %>
								<article class="style3">
										<span class="image">
											<img src="images/pic03.jpg" alt="" />
										</span>
										<a onclick="checkBorrowedItems()">
											<h2>Borrowed</h2>
											<div class="content">
												<p>View the items that have been issued to you and you have not returned them</p>
											</div>
										</a>
									</article>
								<article class="style3">
									<span class="image">
										<img src="images/pic03.jpg" alt="" />
									</span>
									<a  onclick="checkLostItems()">
										<h2>Lost</h2>
										<div class="content">
											<p>View the items that you have lost.</p>
										</div>
									</a>
								</article>
						
							    <%
								  String studentOption = "\t\t<article class=\"style3\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t<span class=\"image\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t\t<img src=\"images/pic04.jpg\" alt=\"\" />\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t<a  onclick=\"changeUserDetails()\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t\t<h2>Details</h2>\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"content\">\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t\t\t<p>Change your personal details</p>\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
										  "\t\t\t\t\t\t\t\t\t\t\t</a>\n" +
										  "\t\t\t\t\t\t\t\t\t\t</article>";
								%>
								<%=userRole.equals("student") ? studentOption : userRole.equals("captain") ? studentOption : "" %>
								<article class="style3">
									<span class="image">
										<img src="images/pic05.jpg" alt="" />
									</span>
									<a onclick="changeUserPassword()">
										<h2>Password</h2>
										<div class="content">
											<p>Chnage the current login Password.</p>
										</div>
									</a>
								</article>
					
							</section>
						</div>
					</div>
	
			<footer class="d-flex justify-content-center pt-3">
					<h3 class="text-center" style="color:black;text-transform:none;" style="font-size:10pt;" >&copy; Egerton 2019</h3>
			</footer>
			
			<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg modal-dialog-centered" role="document">
				  <div class="modal-content">
						
					<div class="modal-header row">
						  <div class="col-md-8">
									<h5 class="modal-title text-right" id="modalTitle"></h5>
							</div>
							<div class="col-md-4">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
									</button>
							</div>
					</div>
					<div class="modal-body" id="modalMain">
					   
					</div>
					<div class="modal-footer">
					  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					</div>
				  </div>
				</div>
			  </div>

		    <!-- Scripts -->
			<script>
			    var userId = "<%=userId%>"  
			</script>
			<script src="./vendor/jquery/jquery.min.js"></script>
			<script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
			<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
			<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
			<script src="vendor/axios.min.js"></script>
			<script src="vendor/parsley/parsley.min.js"></script>
			  <script src="vendor/select2/select2.min.js"></script>
			<script src="vendor/sweetalert/sweetalert2.min.js"></script>
			<script src="./vendor/browser.min.js"></script>
			<script src="./vendor/breakpoints.min.js"></script>
			<script src="./vendor/util.js"></script>
			<script src="./vendor/indexmain.js"></script>
		  <script src="./js/navigationuser.js" ></script>
          
		  <!--Logout Script -->
		  <script>
		    document.getElementById("logBtn").onclick = () => {
              Swal.fire({
				text: `Log Out !`,
				type: 'info',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes'
			  })
			  .then(rs =>{
			      if(rs.value){
				
					axios.delete(`/login?userId=${userId}`)
					.then(data=>{
						//remove all cookies  
						var cookies = document.cookie.split(";");
						for (var i = 0; i < cookies.length; i++) {
							var cookie = cookies[i];
							var eqPos = cookie.indexOf("=");
							var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
							document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
						}
					   //set path
					   window.location.pathname = "/login"
					})
					.catch(err=>{
						console.log(err)
					})
					
				  }	   
			   })
			}
		  </script>
	</body>
</html>