<template>
  <v-card>
    <v-card-title>
      Carts
      <v-spacer></v-spacer>
    <v-btn @click="emptyCart">Empty Cart</v-btn>
      <v-btn @click="addDiscount">Apply Discount</v-btn>
        
    </v-card-title>
    
    <v-data-table
        :headers="headers"
        :items="productsInCart"
        :search="search"
    ></v-data-table>
    <hr />
    <p style="margin-bottom:1cm;">Total in Cart: {{totalPrice - totalPrice * (discountForCart/100) }} 
      <v-spacer></v-spacer>
      Applied Discount: {{discountForCart}}
      <v-card-title>
       <v-flex justify-center>
            <v-btn @click="buyFunc">Buy</v-btn>
      </v-flex>
      <v-spacer></v-spacer>
    <v-btn @click="requestDiscount">Request Discount</v-btn>
      
        
    </v-card-title>
     
      
    </p>
    

    <p style="margin-bottom:3cm;">Client Amount: {{test }}</p>
    <hr />

    <v-data-table
        :headers="headersSecond"
        :items="products"
        :search="search"
        @click:row="addToCart"
    ></v-data-table>
    
    <CartDiscountDialog
        @messageFromChild="setDiscount"
        :opened="dialogVisible"
        :cart="selectedCart"
        @refresh="refreshList"
    ></CartDiscountDialog>
  </v-card>
</template>

<script>
import api from "../api";
import { auth as store } from "../store/auth.module";
import CartDiscountDialog from "../components/CartDiscountDialog";
//import { bus } from "../main";

export default {
  name: "Cart",
  components: {CartDiscountDialog},
  data() {
    return {
      totalPrice : 0,
      //test: store.state.user.wallet,
      test: localStorage.getItem("walletUser"),
      productsInCart: [],
      carts: [],
      discountForCart: 0,
      products: [],
      search: "",
      headers:[
        { text: "Id", value: "id" },
        { text: "Name", value: "name" },
        { text: "Price", value: "price"},
      ],
      headersSecond: [
        { text: "Id", value: "id" },
        { text: "Name", value: "name"},
        { text: "Price", value: "price"},
        { text: "Quantity", value: "quantity"}

      ],

      dialogVisible: false,
      selectedCart: {},
    };
  },
  methods: {
    
    requestDiscount(){
      this.$root.$emit("messageFromClient");
    },
    buyFunc(){
      console.log("entered in BUY");
      console.log(this.productsInCart);
      this.totalPrice = this.totalPrice - this.totalPrice * (this.discountForCart/100)
      if(this.totalPrice> this.test){
        alert("You don't have enough money to buy these products!");
      }
      else{
        api.cart.create({
          client : store.state.user.id,
          products : this.productsInCart
        },
        this.discountForCart
        );

        this.discountForCart = 0;
        this.productsInCart = [];
        this.test -= this.totalPrice; 
        localStorage.setItem("walletUser", this.test+"");
        // console.log(store.state.user.wallet);
        // store.state.user.wallet -= this.totalPrice;
        // console.log(store.state.user.wallet);
        this.totalPrice = 0;

        this.refreshList();
      }
    },

    addToCart(product){
      this.selectedCart = product;
      if(product.quantity>0){
      this.productsInCart.push(product); 
      this.totalPrice += product.price;
      product.quantity--;
      }
      else
      {
        alert("This product is out of stock!");
      }
    },
   
    setDiscount(value){
        this.discountForCart = value;
    },

    addDiscount() {
      this.dialogVisible = true;
      //this.discount 
    },
    
    emptyCart() {
      this.productsInCart = [];
      this.totalPrice = 0;
      this.refreshList;
    },

    async refreshList() {
      this.dialogVisible = false;
      //this.test = await store.state.user.wallet;
      this.products = await api.products.allProducts();
      // this.productsInCart = [];
      //this.totalPrice = 0;
      this.products = await api.products.allProducts();
    },
    
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>