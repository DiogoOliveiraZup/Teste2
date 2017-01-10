package com.teste.teste2.teste2.model;

import java.util.List;

/**
 * Created by Computador on 10/01/2017.
 */

public class MovieSearchInfomations {


    /**
     * Response : True
     * totalResults : 1097
     * Search : [{"Type":"movie","Year":"2002","imdbID":"tt0268380","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyNzI1ODA0MF5BMl5BanBnXkFtZTYwODIxODY3._V1_SX300.jpg","Title":"Ice Age"},{"Type":"movie","Year":"2006","imdbID":"tt0438097","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMjAwODg3OTAxMl5BMl5BanBnXkFtZTcwMjg2NjYyMw@@._V1_SX300.jpg","Title":"Ice Age: The Meltdown"},{"Type":"movie","Year":"2009","imdbID":"tt1080016","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMjA4NDI0Mjg4NV5BMl5BanBnXkFtZTcwOTM1NTY0Mg@@._V1_SX300.jpg","Title":"Ice Age: Dawn of the Dinosaurs"},{"Type":"movie","Year":"2012","imdbID":"tt1667889","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMTM3NDM5MzY5Ml5BMl5BanBnXkFtZTcwNjExMDUwOA@@._V1_SX300.jpg","Title":"Ice Age: Continental Drift"},{"Type":"movie","Year":"1997","imdbID":"tt0119349","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BOTI2MzgxNjIwOF5BMl5BanBnXkFtZTcwOTcxMzU1MQ@@._V1_SX300.jpg","Title":"The Ice Storm"},{"Type":"movie","Year":"2016","imdbID":"tt3416828","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMTUwMTk2ODc1OV5BMl5BanBnXkFtZTgwMzU4MzgzOTE@._V1_SX300.jpg","Title":"Ice Age: Collision Course"},{"Type":"movie","Year":"2005","imdbID":"tt0400525","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMTcwNzk2MjQyNl5BMl5BanBnXkFtZTcwMjA0MjEzMQ@@._V1_SX300.jpg","Title":"The Ice Harvest"},{"Type":"movie","Year":"2005","imdbID":"tt0396652","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMTI1NjIzMzg5OF5BMl5BanBnXkFtZTcwMjI2ODcyMQ@@._V1_SX300.jpg","Title":"Ice Princess"},{"Type":"movie","Year":"2011","imdbID":"tt2100546","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BYTMzMjZhNjAtZDBmMS00MzA0LTljNjUtZGNlMGNiYTc5ODg4XkEyXkFqcGdeQXVyNTE0MDY4Mjk@._V1_SX300.jpg","Title":"Ice Age: A Mammoth Christmas"},{"Type":"movie","Year":"1984","imdbID":"tt0087451","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BOTA1YWRlY2EtZGQ5ZS00Yzg3LTk0ZDYtZDMzNTEyYzczZjA3XkEyXkFqcGdeQXVyNDUxNjc5NjY@._V1_SX300.jpg","Title":"The Ice Pirates"}]
     */
    private String Response;
    private String totalResults;
    private List<SearchEntity> Search;

    public void setResponse(String Response) {
        this.Response = Response;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public void setSearch(List<SearchEntity> Search) {
        this.Search = Search;
    }

    public String getResponse() {
        return Response;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public List<SearchEntity> getSearch() {
        return Search;
    }

    public class SearchEntity {
        /**
         * Type : movie
         * Year : 2002
         * imdbID : tt0268380
         * Poster : https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyNzI1ODA0MF5BMl5BanBnXkFtZTYwODIxODY3._V1_SX300.jpg
         * Title : Ice Age
         */
        private String Type;
        private String Year;
        private String imdbID;
        private String Poster;
        private String Title;

        public void setType(String Type) {
            this.Type = Type;
        }

        public void setYear(String Year) {
            this.Year = Year;
        }

        public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }

        public void setPoster(String Poster) {
            this.Poster = Poster;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getType() {
            return Type;
        }

        public String getYear() {
            return Year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public String getPoster() {
            return Poster;
        }

        public String getTitle() {
            return Title;
        }
    }
}
