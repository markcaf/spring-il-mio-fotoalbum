<template>
    <section class="mt-2">
        
        <div class="container-fluid">
            <div class="row mb-3">
                <div class="col-2" v-for="photo in photos"  :key="photo.id" :class="photo.visible ? '' : 'd-none'">
                    <div class="card" :class="photo.visible ? ' ' : 'd-none'">
                        <div v-if="photo.visible">
                            <img :src="photo.imageUrl" class="card-img-top" :alt="photo.title">
                            <div class="card-body d-flex justify-content-between">
                                <span class="card-text fw-semibold">{{photo.title}}</span>
                                <span class="card-text fw-lighter">#{{photo.tag}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>
</template>

<script>
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/1/';

export default {
    name: 'MainComponent',
    data() {
        return {
            photos: []
            
        }
    },

    methods: {
        getPhotos() {
            axios.get(API_URL + 'photo/all')
            .then(response => {
                this.photos = response.data;
            })
            .catch(error => {
                console.log(error);
            });
        }
    },

    mounted() {
        this.getPhotos();
    }
}
</script>

<style lang="scss" scoped>

</style>