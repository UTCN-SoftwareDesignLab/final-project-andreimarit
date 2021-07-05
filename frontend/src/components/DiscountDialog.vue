<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Discount Dialog" : "Edit discount" }}
          
        </v-toolbar>
        <v-form>
          <v-text-field v-model="discount.id" label="Id" />
          <v-text-field v-model="discount.client" label="Client" />
          <v-text-field v-model="discount.percent" label="Percent" />
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
  name: "DiscountDialog",
  props: {
    discount: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.discount
          .create({
            client: this.discount.client,
            percent: this.discount.percent,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.discount
          .edit({
            id: this.discount.id,
            client: this.discount.client,
            percent: this.discount.percent,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    Delete() {
        api.discount.delete(this.discount.id).then(() => this.$emit("refresh"));
    }
  },
  computed: {
    isNew: function () {
      return !this.discount.id;
    },
  },
};
</script>

<style scoped></style>
