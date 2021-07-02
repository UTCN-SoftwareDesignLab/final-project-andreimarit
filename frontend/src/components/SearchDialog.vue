<template>
  <v-card>
    <v-toolbar color="primary" dark>
      {{ "Search book by NAG" }}
    </v-toolbar>
    <v-form>
      <v-text-field v-model="title" label="introduce the title" />
      <v-text-field v-model="author" label="introduce the author" />
      <v-text-field v-model="genre" label="introduce the genre" />
    </v-form>
    <v-card-actions>
      <v-btn @click="doSearch">{{ "Search" }} </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import api from "../api";
export default {
  name: "SearchDialog",
  props: {
    title: String,
    author: String,
    genre: String,
  },
  methods: {
    doSearch() {
      if (title && author && genre) {
        api.books.searchTAG(title, author, genre).then(() => {
          return this.$emit("refresh");
        });
      } else this.$emit("refresh");
    },
  },
};
</script>

<style scoped></style>