package com.sata.izonovel.Model;

public class RegisterRequestModel {
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    private  String dataSource;
    private String database;
    private String collection;

    public RegisterRequestModel.Document getDocument() {
        return document;
    }

    public void setDocument(RegisterRequestModel.Document document) {
        this.document = document;
    }

    private Document document;
    public static class Document{
        private String username;
        private String password;
        private String fullName;

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
