<template>
    <q-page>
       <div class="row justify-center q-pt-md">
           <div class="col-md-6 col-xs-26">
                <q-card class="my-card">
                <q-card-section>
                    <div class="text-h4 q-pb-md"><center> INPUT DATA HOTEL </center></div>
                    <q-form
      @submit="onSubmit"
      class="q-gutter-sm"
    >
       <q-input
        filled
        type="text"
        v-model="nama_hotel"
        label= "Masukkan nama hotel Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="alamat"
        label= "Masukkan alamat hotel Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="jumlah_kamar"
        label= "Masukkan jumlah kamar hotel Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="fasilitas_kamar"
        label= "Masukkan fasilat kamar hotel Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="harga"
        label= "Masukkan harga hotel Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="no_hotel"
        label= "Masukkan nomor hotel Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="email"
        label= "Masukkan email hotel Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <div>
        <q-btn label="SIMPAN" type="submit" color="primary"/>
      </div>
    </q-form>
                </q-card-section>
                </q-card>
           </div>
       </div>
    </q-page>
</template>

<script>
export default {
  data () {
    return {
      nama_hotel: '',
      alamat: '',
      jumlah_kamar: '',
      fasilitas_kamar: '',
      harga: '',
      no_hotel: '',
      email: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('ihotel/input', {
        nama_hotel: this.nama_hotel,
        alamat: this.alamat,
        jumlah_kamar: this.jumlah_kamar,
        fasilitas_kamar: this.fasilitas_kamar,
        harga: this.harga,
        no_hotel: this.no_hotel,
        email: this.email
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.$router.push('/home')
        }
        this.nama_hotel = ''
        this.alamat = ''
        this.jumlah_kamar = ''
        this.fasilitas_kamar = ''
        this.harga = ''
        this.no_hotel = ''
        this.email = ''
      })
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: 'color'
      })
    }
  }
}
</script>
