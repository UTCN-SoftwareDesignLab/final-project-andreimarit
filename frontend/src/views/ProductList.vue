<template>
  <v-card>
    <v-card-title>
      Products
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-card class=text-center>
      <v-btn @click="addProduct">Add Product</v-btn>
      <v-btn color="red" @click="deleteProduct">Delete Product</v-btn>
      <v-btn @click="editProduct">Edit Product</v-btn>
      <v-btn @click="discountGoTo">GoTo Discount</v-btn>
      <v-btn @click="csvReport">Report CSV</v-btn>
    </v-card>
    <v-data-table
      :headers="headers"
      :items="products"
      @click:row="editProduct"
      :search="search"
    ></v-data-table>
    <ProductDialog
      :opened="dialogVisible"
      :product="selectedProduct"
      @refresh="refreshList"
    ></ProductDialog>
  </v-card>
</template>

<script>
import api from "../api";
import ProductDialog from "../components/ProductDialog";
import router from "../router";

export default {
  name: "ProductList",
  components: { ProductDialog },
  data() {
    return {
      products: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Quantity", value: "quantity" },
        { text: "Price", value: "price" },

      ],
      dialogVisible: false,
      selectedProduct: {},
    };
  },
  methods: {

      async refreshList() {
      this.dialogVisible = false;
      this.selectedProduct = {};
      this.products = await api.products.allProducts();
    },


csvReport() {
      api.products.csvR();
    },
    editProduct(product) {
      this.selectedProduct = product;
      this.dialogVisible = true;
    },
    deleteProduct() {
      //this.selectedProduct = product;
      this.dialogVisible = true;
    },
    addProduct() {
      this.dialogVisible = true;
    },
    discountGoTo(){router.push("./discount")},
    // sellProduct(product) {
    //   this.selectedProduct = product;
    //   this.dialogVisible = true;
    // },
   
  },
  
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>