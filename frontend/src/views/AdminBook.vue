<template>
  <v-card>
    <v-card-title>
      Books for Admin
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-card class=text-center>
      <v-btn @click="addBook">Add Book</v-btn>
      <v-btn @click="deleteBook">Delete Book</v-btn>
      <v-btn @click="editBook">Edit Book</v-btn>
      <v-btn color="orange" @click="goToUsers">Users</v-btn>
      </v-card>
      
      <v-card class=text-center>
       <v-btn @click="csvReport">Report CSV</v-btn>
       <v-btn @click="pdfReport">Report PDF</v-btn>
       <v-btn @click="pdfReportJasper">Report PDF Jasper</v-btn>
      </v-card>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="books"
      :search="search"
      @click:row="editBook"
    ></v-data-table>
    <BookDialog
      :opened="dialogVisible"
      :book="selectedBook"
      @refresh="refreshList"
    ></BookDialog>
  </v-card>
</template>

<script>
import api from "../api";
import BookDialog from "../components/BookDialog";
import router from "../router";

export default {
  name: "BookList",
  components: { BookDialog },
  data() {
    return {
      books: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Author", value: "author" },
        { text: "Genre", value: "genre" },
        { text: "Quantity", value: "quantity" },
        { text: "Price", value: "price" },
      ],
      dialogVisible: false,
      selectedBook: {},
    };
  },
  methods: {
    editBook(book) {
      this.selectedBook = book;
      this.dialogVisible = true;
    },
    addBook() {
      this.dialogVisible = true;
    },
    goToUsers(){router.push("./user")},

    csvReport() {
      console.log("the button has been pressed");
      api.books.csvR();
    },
    pdfReport() {
      console.log("the button has been pressed");
      api.books.pdfR();
    },
    pdfReportJasper() {
      console.log("the button has been pressed");
      api.books.pdfJasper();
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedBook = {};
      this.books = await api.books.allBooks();
    },
    deleteBook(book){
        this.selectedBook = book;
        this.dialogVisible = true;
      },
  },
  created() {
    this.refreshList();
  },
  
};
</script>

<style scoped></style>
