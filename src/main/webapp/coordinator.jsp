<%
  boolean found = false;
  if(request.getCookies() != null && request.getCookies().length > 3){
    for(Cookie c  : request.getCookies()){
      if(c.getName().equals("egersportuserrole")) {
        if(c.getValue().equals("coordinator")){
          found = true;
        }
      }
    }
  }

  if(!found)response.sendRedirect("/adminLogin");
%>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Sports Store Management System</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">

  <link rel="stylesheet" href="vendor/sweetalert/sweetalert2.min.css"> 

  <link rel="stylesheet" href="vendor/parsley/parsley.css">

  <link rel="stylesheet" href="vendor/select2/select2.min.css">
  
  <link rel="stylesheet" href="vendor/autocomplete/autocomplete.min.css">


</head>

<body id="page-top">

    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
      <i class="fas fa-bars"></i>
    </button>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto mr-0 mr-md-3 my-2 my-md-0">
      <li class="nav-item dropdown no-arrow">
        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user-circle fa-fw"></i> Coordinator
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" id="logBtn">Logout</a>
        </div>
      </li>
    </ul>
  </nav>

  <div id="wrapper">
    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="storekeeper.html">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span>
        </a>
      </li>
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span>Register</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <a class="dropdown-item" onclick="registerSport()">Sport</a>
            <a class="dropdown-item" onclick="registerStudent()">Student</a>
            <a class="dropdown-item" onclick="registerTeam()">Team</a>
            <a class="dropdown-item" onclick="registerCoach()">Coach</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span>Fields</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <a class="dropdown-item" onclick="registerField()">Add Field</a>
          </div>
        </li>
          <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span>View</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <a class="dropdown-item" onclick="viewStudents()">Students</a>
            <a class="dropdown-item" onclick="viewTeams()">Teams</a>
            <a class="dropdown-item" onclick="viewCoaches()">Coaches</a>
            <a class="dropdown-item" onclick="viewSports()">Sports</a>
            <a class="dropdown-item" onclick="viewFields()">Fields</a>
            <a class="dropdown-item" onclick="viewgames()">View Games</a>
          </div>
        </li>
        <li class="nav-item">
        <a class="nav-link" onclick="viewItems()">
          <span>View Stock</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" onclick="gameRequests()">
          <span>Game Requests</span></a>
      </li>
    </ul>
     
    <div id="content-wrapper" style="background-color:#e9ecef !important;">
       
        <!-- center working window -->
      <div class="container d-flex justify-content-center" id="main-window">
          <div class="wrapper">      
              
          </div>
      </div>
      <!-- /.container-fluid -->

      <!-- Sticky Footer -->
      <footer class="sticky-footer">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright © Your Website 2019</span>
          </div>
        </div>
      </footer>

    </div>
    <!-- /.content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>


   <!-- Bootstrap core JavaScript-->
   <script src="vendor/jquery/jquery.min.js"></script>
   <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
   <!-- Core plugin JavaScript-->
   <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
   <!-- Page level plugin JavaScript-->
   <script src="vendor/chart.js/Chart.min.js"></script>
   <script src="vendor/datatables/jquery.dataTables.min.js"></script>
   <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
   <script src="vendor/sweetalert/sweetalert2.min.js"></script>
   <!-- Custom scripts for all pages-->
   <script src="js/sb-admin.min.js"></script>
   <script src="vendor/axios.min.js"></script>
   <script src="vendor/parsley/parsley.min.js"></script>
   <script src="vendor/select2/select2.min.js"></script>
   <script src="vendor/autocomplete/liquidmetal.js"></script>
   <script src="vendor/autocomplete/autocomplete.min.js"></script>
   
   <script src="js/main.js"></script>
   <script>
      user = "coordinator"
   </script>
  <script src="js/navigation.js"></script>
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
				
					axios.delete(`/login?userId=${user}`)
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
					   window.location.pathname = "/adminLogin"
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
