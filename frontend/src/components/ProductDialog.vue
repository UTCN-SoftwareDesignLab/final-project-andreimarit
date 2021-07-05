<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Product Dialog" : "Edit product" }}
          
        </v-toolbar>
        <v-form>
          <v-text-field v-model="product.id" label="Id" />
          <v-text-field v-model="product.name" label="Name" />
          <v-text-field v-model="product.quantity" label="Quantity" />
          <v-text-field v-model="product.price" label="Price" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="Delete">
            Delete
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ProductDialog",
  props: {
    product: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.products
          .create({
            name: this.product.name,
            quantity: this.product.quantity,
            price: this.product.price,
            
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.products
          .edit({
            id: this.product.id,
            name: this.product.name,
            quantity: this.product.quantity,
            price: this.product.price,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    Delete() {
        api.products.deleteProduct(this.product.id).then(() => this.$emit("refresh"));
    }
  },
  computed: {
    isNew: function () {
      return !this.product.id;
    },
  },
};
</script>

<style scoped></style>
