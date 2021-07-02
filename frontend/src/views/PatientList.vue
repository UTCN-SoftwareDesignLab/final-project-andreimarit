<template>
  <v-card>
    <v-card-title>
      Patients
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
      <v-btn @click="addPatient">Add Patient</v-btn>
      <v-btn color="red" @click="deletePatient">Delete Patient</v-btn>
      <v-btn @click="editPatient">Edit Patient</v-btn>
      <v-btn @click="csvReport">Report CSV</v-btn>
      <v-btn @click="consultationSecretary">GoTo Consultations</v-btn>
    </v-card>
    <v-data-table
      :headers="headers"
      :items="patients"
      @click:row="editPatient"
      :search="search"
    ></v-data-table>
    <PatientDialog
      :opened="dialogVisible"
      :patient="selectedPatient"
      @refresh="refreshList"
    ></PatientDialog>
  </v-card>
</template>

<script>
import api from "../api";
import PatientDialog from "../components/PatientDialog";
import router from "../router";

export default {
  name: "PatientList",
  components: { PatientDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Number", value: "phoneNumber" },
      ],
      dialogVisible: false,
      selectedPatient: {},
    };
  },
  methods: {

      async refreshList() {
      this.dialogVisible = false;
      this.selectedPatient = {};
      this.patients = await api.patients.allPatients();
    },

    csvReport() {
      api.patients.csvR();
    },
    
    consultationSecretary(){
      router.push("./consultation");
    },
    editPatient(patient) {
      this.selectedPatient = patient;
      this.dialogVisible = true;
    },
    deletePatient() {
      //this.selectedPatient = patient;
      this.dialogVisible = true;
    },
    addPatient() {
      this.dialogVisible = true;
    },
    // sellPatient(patient) {
    //   this.selectedPatient = patient;
    //   this.dialogVisible = true;
    // },
   
  },
  
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>