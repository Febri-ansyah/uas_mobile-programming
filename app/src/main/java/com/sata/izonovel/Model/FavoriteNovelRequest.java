package com.sata.izonovel.Model;

public class FavoriteNovelRequest {

    private String collection;
    private String database;
    private String dataSource;
    private Filter filter;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public static class Filter{
        public String getisFavorit() {
            return isFavorit;
        }

        public void setisFavorit(String isFavorit) {
            this.isFavorit = isFavorit;
        }



        private String isFavorit;

    }
}