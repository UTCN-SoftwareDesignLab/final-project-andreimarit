<template>
  <v-card>
    <v-toolbar color="primary" dark>
      {{ "Sell book" }}
    </v-toolbar>
    <v-form>
      <v-text-field v-model="item.title" label="Title" />
      <v-text-field v-model="quantity" label="Quantity for sell" />
    </v-form>
    <v-card-actions>
      <v-btn @click="doSell">{{ "Sell" }} </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import api from "../api";
export default {
  name: "SellDialog",
  props: {
    item: Object,
    quantity: Number,
    opened: Boolean,
  },
  methods: {
    doSell() {
      if (this.item.quantity >= this.quantity) {
        api.bookstore.sell(this.item.id, this.quantity).then(() => {
          return this.$emit("refresh");
        });
      } else this.$emit("refresh");
    },
  },
};
</script>

<style scoped></style>