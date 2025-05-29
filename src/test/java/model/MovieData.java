package model;

public class MovieData {
    private String name;
    private int year;
    private String genre;
    private Crew crew;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Crew getCrew() {
        return crew;

    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public static class Crew {
        private String director;
        private String leadRole;
        private String music;

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getLeadRole() {
            return leadRole;
        }

        public void setLeadRole(String leadRole) {
            this.leadRole = leadRole;
        }

        public String getMusic() {
            return music;
        }

        public void setMusic(String music) {
            this.music = music;
        }
    }
}




