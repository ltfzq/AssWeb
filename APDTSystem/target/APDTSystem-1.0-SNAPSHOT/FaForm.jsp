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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style1.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>UMT Academic Program Development Tracking System</title>
  
  <script>
            window.onload = function() {
               var urlParams = new URLSearchParams(window.location.search);
               if (urlParams.has('success')) {
                 alert('Full Accreditation successfully saved');
               }
            };
    </script>
    
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
      <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
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
          <a href="${pageContext.request.contextPath}/module4.jsp">Full Accreditation</a>
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
       <h1> - Full Accreditation - </h1>
       <center>
           <button style=" padding:2px; border-radius: 5px;"><a href="${pageContext.request.contextPath}/module4.jsp" style="color: black; text-decoration: none;">Management</a></button>
           <button style=" padding:2px; border-radius: 5px;"><a href="${pageContext.request.contextPath}/FaDocForm.jsp" style="color: black; text-decoration: none;">Document</a></button>
           <button style=" padding:2px; border-radius: 5px;"><a href="${pageContext.request.contextPath}/Mqa02Form.jsp" style="color: black; text-decoration: none;">MQA-02</a></button>
           <button style=" padding:2px; border-radius: 5px;"><a href="${pageContext.request.contextPath}/APPForm.jsp" style="color: black; text-decoration: none;">App List</a></button>
           <button style="padding:2px; border-radius: 5px;"><a href="${pageContext.request.contextPath}/IrvForm.jsp" style="color: black; text-decoration: none;">Internal Review</a></button>
           <button style="padding:2px; background-color: lightblue; border-radius: 5px;"><a href="FaForm.jsp" style="color: black; text-decoration: none;">Full Accreditation</a></button>
       </center>
      <fieldset style="border-radius: 5px;">
          <c:if test="${fa != null}">
             <form action="${pageContext.request.contextPath}/fa/update" method="post" >
          </c:if>
          <c:if test="${fa == null}">
              <form action="${pageContext.request.contextPath}/fa/insert" method="post">
          </c:if>
	  
          <h2>
              <c:if test="${fa != null}">
                  Edit Full Accreditation
              </c:if>
              <c:if test="${fa == null}">
                  Add New Full Accreditation
              </c:if>
          </h2>

	  <c:if test="${fa != null}">
              <input type="hidden" name="faid" value="<c:out value='${fa.faid}' />">
          </c:if>

              <table>
                  <tr>
                      <td>
                          <label>Program Code:</label>
                      </td>
                      <td>
                          <input type="text" value="<c:out value='${fa.progcode}' />" 
                                                          name="progcode" required="required" placeholder="e.g.:UG6481001">
                      </td>
                  </tr>
                  
                  <tr>
                      <td>
                          <label>MQA-02 ID:</label>
                      </td>
                      <td>
                          <input type="text" value="<c:out value='${fa.mqa02id}' />" 
                                                            name="mqa02id" placeholder="e.g.:1">
                      </td>
                  </tr>
                  
                  <tr>
                      <td>
                          <label>APP ID:</label>
                      </td>
                      <td>
                          <input type="text" value="<c:out value='${fa.appid}' />" 
                                                            name="appid" placeholder="e.g.:2">
                      </td>
                  </tr>
                  
                  <tr>
                      <td>
                          <label>Internal Review ID:</label>
                      </td>
                      <td>
                          <input type="irvid" value="<c:out value='${fa.irvid}' />" 
                                                            name="irvid" placeholder="e.g.:3">
                      </td>
                  </tr>
                                  
                  <tr>
                      <td>
                          <label>Status:</label>
                      </td>
                      <td>
                            <input type="radio" id="status" name="status" value="approved" <c:if test="${fa.status == 'approved'}">checked</c:if>>
                            <label for="status"> approved </label>
                            <input type="radio" id="status" name="status" value="rejected" <c:if test="${fa.status == 'rejected'}">checked</c:if>>
                            <label for="status"> rejected </label>
                            <input type="radio" id="status" name="status" value="pending" <c:if test="${fa.status == 'pending'}">checked</c:if>>
                            <label for="status"> pending </label>
                        </td>
                  </tr>
                  <tr>
                      <td colspan="2">
                          <input type="submit" id="btnSubmit" value="Submit">
                          <input type="reset" id="btnCancel" value="Cancel">
                      </td>
                  </tr>
              </table>
          </form>
      </fieldset>
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
