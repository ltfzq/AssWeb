<%-- 
    Document   : MQA02
    Created on : Jun 7, 2024, 2:41:20 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/style1.css">
  <link rel="stylesheet" href="css/style2.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>UMT Academic Program Development Tracking System</title>
  
  <style>
      .management-panel {
      background-color: #f4f4f4;
      width: 50%;
      margin: 0 auto;
    }
    .management-panel h1 {
      background-color: tomato;
      color: white;
      padding: 15px;
      border-radius: 8px 8px 0 0;
    }
    .management-panel table {
      width: 100%;
      border-collapse: collapse;
    }
    .management-panel th, .management-panel td {
      border: 1px solid #ddd;
      padding: 12px;
      text-align: center;
    }
    .management-panel th {
      background-color: #87CEEB;
      color: white;
    }    .management-panel a {
      text-decoration: none;
      color: #333;
      font-weight: bold;
    }
    .management-panel a:hover {
      color: tomato;
    }
  </style>
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
          <a href="index.jsp">Full Accreditation</a>
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
  <!-- Main content of the web page -->
  <div class="content">
    <div class="management-panel">
      <center><h1>Full Accreditation Application - Management Panel</h1></center>
      <table>
        <thead>
          <tr>
            <th colspan="3">
              <center>Manage Documents</center>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><a href="http://localhost:8080/APDTSystem/fadoc/listfadoc">View all document📃</a></td>
            <td><a href="http://localhost:8080/APDTSystem/fadoc/new">Add new document➕</a></td>
            <td><a href="http://localhost:8080/APDTSystem/fadoc/listfadoc">Edit Existing Document✏️</a></td>
          </tr>
        </tbody>
        <thead>
          <tr>
            <th colspan="3">
              <center>Manage MQA-02 </center>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><a href="http://localhost:8080/APDTSystem/mqa02/listMqa02">View all MQA-02📃</a></td>
            <td><a href="http://localhost:8080/APDTSystem/mqa02/new2">Add new MQA-02➕</a></td>
            <td><a href="http://localhost:8080/APDTSystem/mqa02/listMqa02">Edit Existing MQA-02✏️</a></td>
          </tr>
        </tbody>
        <thead>
          <tr>
            <th colspan="3">
              <center>Manage Review Panel Member(APP)</center>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><a href="http://localhost:8080/APDTSystem/listfadoc">View all APP🤵</a></td>
            <td><a href="http://localhost:8080/APDTSystem/new">Add new APP➕</a></td>
            <td><a href="http://localhost:8080/APDTSystem/listfadoc">Edit Exitsting APP✏️</a></td>
          </tr>
        </tbody>
        <thead>
          <tr>
            <th colspan="3">
              <center>Manage Internal Review</center>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><a href="http://localhost:8080/APDTSystem/listfadoc">View all Internal Review📆</a></td>
            <td><a href="http://localhost:8080/APDTSystem/new">Add new Internal Review➕</a></td>
            <td><a href="http://localhost:8080/APDTSystem/listfadoc">Edit Existing Internal Review✏️</a></td>
          </tr>
        </tbody>
        <thead>
          <tr>
            <th colspan="3">
              <center>Manage Full Accreditation Application</center>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><a href="http://localhost:8080/APDTSystem/listfadoc">View all FA Application📃</a></td>
            <td><a href="http://localhost:8080/APDTSystem/new">Add new FA Application➕</a></td>
            <td><a href="http://localhost:8080/APDTSystem/listfadoc">Edit Existing FA Application✏️</a></td>
          </tr>
        </tbody>
      </table>
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
