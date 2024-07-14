<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manage Role Change Requests</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: 20px auto;
                background: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
            }
            .search-container {
                text-align: center;
                margin-bottom: 20px;
            }
            .search-input {
                padding: 10px;
                width: 80%;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 16px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 12px;
                text-align: left;
            }
            th {
                background-color: #f4f4f4;
                color: #333;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .action-buttons {
                display: flex;
                gap: 10px;
            }
            .action-buttons form {
                display: inline;
            }
            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
            }
            .btn-approve {
                background-color: #4CAF50;
                color: white;
            }
            .btn-approve:hover {
                background-color: #45a049;
            }
            .btn-reject {
                background-color: #f44336;
                color: white;
            }
            .btn-reject:hover {
                background-color: #e53935;
            }
            .btn-back {
                background-color: #2196F3;
                color: white;
                margin-bottom: 20px;
            }
            .btn-back:hover {
                background-color: #1E88E5;
            }
            .content-preview {
                cursor: pointer;
                color: blue;
                text-decoration: underline;
            }
            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                padding: 20px;
                border: 1px solid #ddd;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                z-index: 1000;
                width: 50%;
                max-height: 80%;
                overflow-y: auto;
            }
            .popup-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 999;
            }
            .popup-close {
                display: block;
                text-align: right;
                margin-bottom: 10px;
            }
            .header-actions {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Manage Role Change Requests</h2>
            <div class="header-actions">
                <button onclick="window.location.href = 'home'" class="btn btn-back">Back Home</button>
                <div class="search-container">
                    <input type="text" id="searchInput" class="search-input" onkeyup="filterTable()" placeholder="Search">
                </div>
            </div>
            <table id="roleRequestsTable">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Fullname</th>
                        <th>Email</th>
                        <th>DOB</th>
                        <th>Phone</th>
                        <th>Current Role</th>
                        <th>Requested Role</th>
                        <th>Content</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="request" items="${roleChangeRequests}">
                        <tr>
                            <td>${request.username}</td>
                            <td>${request.fullname}</td>
                            <td>${request.email}</td>
                            <td>${request.dob}</td>
                            <td>${request.phone}</td>
                            <td>${request.currentRoleName}</td>
                            <td>${request.requestedRoleName}</td>
                            <td>
                                <span class="content-preview" onclick="showPopup('${request.content}')">View Content</span>
                            </td>
                            <td>
                                <div class="action-buttons">
                                    <form action="approveRoleChange" method="post">
                                        <input type="hidden" name="account_id" value="${request.accountId}" />
                                        <input type="hidden" name="new_role_id" value="${request.requestedRoleId}" />
                                        <button type="submit" class="btn btn-approve">Approve</button>
                                    </form>
                                    <form action="rejectRoleChange" method="post">
                                        <input type="hidden" name="account_id" value="${request.accountId}" />
                                        <button type="submit" class="btn btn-reject">Reject</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="popup-overlay" class="popup-overlay" onclick="hidePopup()"></div>
        <div id="popup" class="popup">
            <div class="popup-close"><button onclick="hidePopup()">Close</button></div>
            <div id="popup-content"></div>
        </div>

        <script>
            function showPopup(content) {
                document.getElementById('popup-content').innerText = content;
                document.getElementById('popup-overlay').style.display = 'block';
                document.getElementById('popup').style.display = 'block';
            }

            function hidePopup() {
                document.getElementById('popup-overlay').style.display = 'none';
                document.getElementById('popup').style.display = 'none';
            }

            function filterTable() {
                var input, filter, table, tr, td, i, j, txtValue;
                input = document.getElementById("searchInput");
                filter = input.value.toLowerCase();
                table = document.getElementById("roleRequestsTable");
                tr = table.getElementsByTagName("tr");

                for (i = 1; i < tr.length; i++) {
                    tr[i].style.display = "none";
                    td = tr[i].getElementsByTagName("td");
                    for (j = 0; j < td.length; j++) {
                        if (td[j]) {
                            txtValue = td[j].textContent || td[j].innerText;
                            if (txtValue.toLowerCase().indexOf(filter) > -1) {
                                tr[i].style.display = "";
                                break;
                            }
                        }
                    }
                }
            }
        </script>
    </body>
</html>
