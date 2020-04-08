package cursoandroid.whatsappandroid.com.netflix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cursoandroid.whatsappandroid.com.netflix.model.Category;
import cursoandroid.whatsappandroid.com.netflix.model.Movie;

public class MainActivity extends AppCompatActivity {

    MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       RecyclerView rv = findViewById(R.id.recycler_view_main);

       ArrayList<Category> categories = new ArrayList<>();
       for ( int i = 0; i<10;i++) {
           Category category = new Category();
           category.setName("cat " + i);

           ArrayList<Movie> movies = new ArrayList<>();

           for (int j = 0; j < 30; j++) {
               Movie movie = new Movie();
               movie.setCoverUrl(R.drawable.movie);
               movies.add(movie);
           }

           category.setMovie(movies);
           categories.add(category);
       }

        mainAdapter = new MainAdapter(categories);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(mainAdapter);
    }

    private static class CategoryHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        RecyclerView recyclerViewMovie;

        CategoryHolder (@NonNull View itemView){
            super (itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            recyclerViewMovie = itemView.findViewById(R.id.recycler_view_movie);

        }

    }

    private class MainAdapter extends RecyclerView.Adapter<CategoryHolder>{

        private final ArrayList<Category> categories;
        MainAdapter (ArrayList<Category> categories){
            this.categories = categories;
        }

        @NonNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull ViewGroup  viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.category_item, viewGroup, false);
            return new CategoryHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int i) {
            Category category = categories.get(i);

            categoryHolder.textViewTitle.setText(category.getName());
            categoryHolder.recyclerViewMovie.setAdapter(new MovieAdapter(category.getMovies()));
            categoryHolder.recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                    LinearLayoutManager.HORIZONTAL, false));


        }

        @Override
        public int getItemCount() {
            return categories.size();
        }
    }











    private static class MovieHolder extends RecyclerView.ViewHolder{

        ImageView imageViewUrlCover;

        public MovieHolder(@NonNull View itemView){
            super (itemView);
            imageViewUrlCover = itemView.findViewById(R.id.image_view_cover);
        }
    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieHolder>{

        private final List<Movie> movies;
        public MovieAdapter(List<Movie> movies){
            this.movies = movies;
        }
        @NonNull
        @Override
        public MovieHolder onCreateViewHolder(@NonNull ViewGroup  viewGroup, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.movie_item, viewGroup, false);
            return new MovieHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
            Movie movie = movies.get(i);
            movieHolder.imageViewUrlCover.setImageResource(movie.getCoverUrl());

        }

        @Override
        public int getItemCount() {
            return movies.size();
        }
    }
}
