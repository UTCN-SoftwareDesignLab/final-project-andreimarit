<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Patient Dialog" : "Edit patient" }}
          
        </v-toolbar>
        <v-form>
          <v-text-field v-model="patient.id" label="Id" />
          <v-text-field v-model="patient.name" label="Name" />
          <v-text-field v-model="patient.phoneNumber" label="Number" />
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
  name: "PatientDialog",
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.patients
          .create({
            name: this.patient.name,
            phoneNumber: this.patient.phoneNumber,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.patients
          .edit({
            id: this.patient.id,
            name: this.patient.name,
            phoneNumber: this.patient.phoneNumber,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    Delete() {
        api.patients.deletePatient(this.patient.id).then(() => this.$emit("refresh"));
    }
  },
  computed: {
    isNew: function () {
      return !this.patient.id;
    },
  },
};
</script>

<style scoped></style>
