<template>
    <section class="mt-2">
        
        <div class="container-fluid">
            <div class="row mb-4 justify-content-center">
                <form class="col-8" id="search_bar">
                    <!-- <div class="input-group">
                        <input type="text" class="form-control me-2" v-model="searchValue" placeholder="Insert a title or a tag to search between photos, then click on the Search button" @keyup="getSearchPhotos()" aria-label="Search" aria-describedby="basic-addon1">
                        <button class="btn btn-outline-success" id="basic-addon1" @click="getSearchPhotos()">Search</button>
                    </div> -->

                    <form class="container-fluid" id="search_bar">
                        <div class="input-group">
                            <span class="input-group-text" id="basic-addon1">Search</span>
                            <input type="text" class="form-control" v-model="searchValue" @keyup="getSearchPhotos()" placeholder="Insert a title or a tag to search between photos, press enter to clear" aria-label="Search" aria-describedby="basic-addon1">
                        </div>
                    </form>
                </form>
            </div>

            <div class="row mb-3">
                <div class="col-2" v-for="photo in photos"  :key="photo.id" :class="photo.visible ? '' : 'd-none'"
                @click="setActivePhotoIndex(getIndexFromPhotoId(photo.id)), getPhotoCategories(photo.id)">
                    <div class="card" :class="photo.visible ? ' ' : 'd-none'">
                        <div v-if="photo.visible">
                            <img :src="photo.imageUrl" class="card-img-top" :alt="photo.title">
                            <div class="card-body d-flex justify-content-between">
                                <span class="card-text fw-semibold">{{photo.title}}</span>
                                <span class="card-text fw-lighter">#{{photo.tag}}</span>
                            </div>

                            <div v-if="activePhotoIndex === getIndexFromPhotoId(photo.id)">
                                <div v-if="photo.categories != null" class="card-body">
                                    Categories: <span class="d-inline-block me-1" v-for="category in photo.categories" :key="category.id">#{{category.name}} </span>
                                </div>

                                <div class="card-body" v-if="photo.comments != null">
                                    <span class="card-text">{{photo.description}}</span>

                                    <h6 class="mt-3">Comments:</h6>
                                    <div class="card mb-2">
                                        <ul class="list-group list-group-flush">
                                            <li v-for="comment in photo.comments" :key="comment.id" class="list-group-item">
                                                {{comment.text}}
                                            </li>
                                        </ul>
                                    </div>

                                    <span class="fst-italic">write a comment:</span>
                                    <div class="d-flex align-items-center mb-2">
                                    <input type="text" class="form-control" @keyup.enter="postComment(photo.id)" placeholder="..." v-model="userComment">
                                    </div>
                                </div>
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
const ACTIVE_PICTURE_INDEX = -1;

export default {
    name: 'MainComponent',
    data() {
        return {
            photos: [],
            activePhotoIndex: ACTIVE_PICTURE_INDEX,
            searchValue: '',
            userComment: '',
            newComment: {text: '', photo: ''},
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
        },

        getPhotoCategories(photoId){
        axios.get(API_URL + 'category/by/photo/' + photoId)
            .then(response => {
            const photoCategories = response.data;
            if (photoCategories == null) return
            const index = this.getIndexFromPhotoId(photoId);
            this.photos[index].categories = photoCategories;
            })
            .catch(error => {
            console.log(error);
            });
        },

        setActivePhotoIndex(index){
        this.activePhotoIndex = index;
        console.log("Set: " + this.activePhotoIndex);
        },

        getIndexFromPhotoId(id){
        return this.photos.findIndex(photo => photo.id === id);
        },

        getSearchPhotos(){
        if(this.searchValue === '') return this.getPhotos();
            axios.get(API_URL + 'photo/search/' + this.searchValue)
                .then(response => {
                this.photos = response.data;
            })
            .catch(error => {
                console.log(error);
            });
        },

        getPhotoComments(photoId) {
        axios.get(API_URL + 'comments/by/photos/' + photoId)
            .then(response => {
                const photoComments = response.data;
                if (photoComments == null) return
                const index = this.getIndexFromPhotoId(photoId);
                this.photos[index].comments = photoComments;
            })
            .catch(error => {
                console.log(error);
            });
        },

        postComment(photoId){
            this.newComment.photo = photoId;
            this.newComment.text = this.userComment;
            axios.post(API_URL + 'comments/add/' + photoId , this.newComment)
                .then(response => {
                this.getPhotoComments(photoId);
                this.userComment = '';
                console.log(response);
            })
            .catch(error => {
            console.log(error);
            });
        },
    },

    mounted() {
        this.getPhotos();
    }
}
</script>

<style lang="scss" scoped>

</style>