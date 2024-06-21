<%-- 
    Document   : index
    Created on : Jun 19, 2024, 11:03:51 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/style1.css">
  <link rel="stylesheet" href="css/style2.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>UMT Academic Program Development Tracking System</title>
</head>

<body>
  <!-- This the header of the page-->
  <div class="header">
    <header>
      <table>
        <tr>
          <td class="logoumtcol"><img class="logoumt" src="https://upload.wikimedia.org/wikipedia/commons/3/3e/Logo_Rasmi_UMT.png"></td>
          <td><h1>UMT Academic Program Development Tracking System</h1></td>
        </tr>
      </table>
    </header>
  </div>

  <!-- Navigation bar of the page -->
  <div class="navbar">
    <ul>
      <li><a href="index.jsp">Home</a></li>
      <li><a href="#programlist.html">Program List</a></li>
      <li><a href="contact.html">Contact</a></li>
      <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Manage Application</a>
        <div class="dropdown-content">
          <a href="jppu.jsp">Internal Application</a>
          <a href="javascript:void(0)" class="subdropbtn">Provisional Accreditation ></a>
            <div class="sub-dropdown-content">
              <a href="/task2/task2.html">MQA-01</a>
              <a href="#">JPT</a>
            </div>
          <a href="/task2/task2upgraded.html">Program Offering</a>
          <a href="module4.jsp">Full Accreditation</a>
          <a href="module5.html">MQA Certification</a>
        </div>
      </li>
      <li class="profile">
        <a href="javascript:void(0)" class="dropbtn"><img class="iconProfile" src="https://static.vecteezy.com/system/resources/previews/019/879/186/original/user-icon-on-transparent-background-free-png.png">Profile</a>
        <div class="dropdown-content">
            <a href="#">Logout</a>
        </div>
      </li>
    </ul>
  </div>

  <!-- here where the main content of the web page started-->
  <div class="content">
      <div>
          <center><h2>Notifications!</h2></center><hr>
          <center>
              <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
               <p>Submission for JPT and MQA-01 today! <a href="#">Click here to view</a></p>
          </center>
      </div>
  </div>

  <!-- footer of the page-->
  <div class="footer">
    <footer>&copy; Pusat Pengurusan dan Penjaminan Kualiti UMT 2024</footer>
  </div>

  <!-- javascript sources -->
  <script type="text/javascript" src="js/script1.js"></script>
  <script type="text/javascript" src="js/script2.js"></script>
</body>
</html>
