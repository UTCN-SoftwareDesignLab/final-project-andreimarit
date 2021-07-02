<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create consultation" : "Edit consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.id" label="Id" />
          <v-text-field v-model="consultation.date" label="Date" />
          <v-text-field v-model="consultation.patient" label="Patient" />
          <v-text-field v-model="consultation.doctor" label="Doctor" />          
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
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultation
            .create({
              date: this.consultation.date,
              patient: this.consultation.patient,
              doctor: this.consultation.doctor,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.consultation
            .edit({
              id: this.consultation.id,
              date: this.consultation.date,
              patient: this.consultation.patient,
              doctor: this.consultation.doctor,
              
            })
            .then(() => this.$emit("refresh"));
      }
    },
     Delete() {
        api.consultation.delete(this.consultation.id).then(() => this.$emit("refresh"));
    }
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>