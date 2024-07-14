/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class RoleChangeRequest {



        private int accountId;
        private String username;
        private String fullname;
        private String email;
        private String dob;
        private String phone;
        private int currentRoleId;
        private String currentRoleName;
        private int requestedRoleId;
        private String requestedRoleName;
        private String content;

        public RoleChangeRequest() {
        }

        public RoleChangeRequest(int accountId, String username, String fullname, String email, String dob, String phone, int currentRoleId, String currentRoleName, int requestedRoleId, String requestedRoleName, String content) {
            this.accountId = accountId;
            this.username = username;
            this.fullname = fullname;
            this.email = email;
            this.dob = dob;
            this.phone = phone;
            this.currentRoleId = currentRoleId;
            this.currentRoleName = currentRoleName;
            this.requestedRoleId = requestedRoleId;
            this.requestedRoleName = requestedRoleName;
            this.content = content;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getCurrentRoleId() {
            return currentRoleId;
        }

        public void setCurrentRoleId(int currentRoleId) {
            this.currentRoleId = currentRoleId;
        }

        public String getCurrentRoleName() {
            return currentRoleName;
        }

        public void setCurrentRoleName(String currentRoleName) {
            this.currentRoleName = currentRoleName;
        }

        public int getRequestedRoleId() {
            return requestedRoleId;
        }

        public void setRequestedRoleId(int requestedRoleId) {
            this.requestedRoleId = requestedRoleId;
        }

        public String getRequestedRoleName() {
            return requestedRoleName;
        }

        public void setRequestedRoleName(String requestedRoleName) {
            this.requestedRoleName = requestedRoleName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "RoleChangeRequest{" + "accountId=" + accountId + ", username=" + username + ", fullname=" + fullname + ", email=" + email + ", dob=" + dob + ", phone=" + phone + ", currentRoleId=" + currentRoleId + ", currentRoleName=" + currentRoleName + ", requestedRoleId=" + requestedRoleId + ", requestedRoleName=" + requestedRoleName + ", content=" + content + '}';
        }
    

}
