package com.sata.izonovel.Model;


public class UserModel {
    public UserModel.document getDocument() {
        return document;
    }

    public void setDocument(UserModel.document document) {
        this.document = document;
    }

    private document document;

    public class document{
        private String _id;
        private String username;
        private String password;
        private String fullName;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }
}
