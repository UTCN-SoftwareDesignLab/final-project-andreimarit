<template>
  <v-card>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="addConsultation">Add Consultation</v-btn>
      <v-btn @click="deleteConsultation">Delete Consultation</v-btn>
      <v-btn @click="editConsultation">Edit Consultation</v-btn>
      <v-btn @click="pdfReport">Report PDF</v-btn>
      <v-btn @click="patient">GoTo Patients</v-btn>
      <v-btn @click="sendEmail">SendEmail</v-btn>
      <v-btn @click="sendSMS">SendSMS</v-btn>      
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="consultations"
        :search="search"
        @click:row="editConsultation"
        
    ></v-data-table>
    <ConsultationDialog
        :opened="dialogVisible"
        :consultation="selectedConsultation"
        @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import api from "../api";
import router from "../router";
import ConsultationDialog from "../components/ConsultationDialog";
export default {
  name: "ConsultationSecretary",
  components: {ConsultationDialog},
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        { text: "Id", value: "id" },
        { text: "Patient", value: "patient" },
        { text: "Doctor", value: "doctor" },
        { text: "Date", value: "date" },
      ],
      dialogVisible: false,
      selectedConsultation: {},
    };
  },
  methods: {
    patient(){
      router.push("./patient");
    },
    editConsultation(consultation) {
      this.selectedConsultation = consultation;
      this.dialogVisible = true;
    },
    deleteConsultation(){
      //this.selectedConsultation = consultation;
      this.dialogVisible = true;
    },
    addConsultation() {
      this.dialogVisible = true;
    },
    pdfReport() {
      api.consultation.pdfR();
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedConsultation = {};
      this.consultations = await api.consultation.allConsultations();
    },
    goToPatients(){router.push("./patient")},
    sendEmail(){
      console.log("entered in sendEmail in View");
      api.consultation.sendEmail();
      },

    sendSMS() {api.consultation.sendSMS()},
    
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>