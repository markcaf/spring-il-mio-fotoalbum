<template>
  <header>
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">
                <img src="https://upload.wikimedia.org/wikipedia/commons/0/08/Pinterest-logo.png" alt="Logo">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active btn btn-dark px-3 rounded-pill text-white me-2" aria-current="page" href="#">Home Page</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Photos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Categories</a>
                    </li>
                </ul>

                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link btn btn-primary rounded-pill text-white px-3" aria-current="page" href="http://localhost:8080/login">Login</a>
                    </li>
                </ul>
                
            </div>
        </div>
    </nav>
  </header>
</template>

<script>
export default {
    name: 'HeaderComponent',
    data() {
        return {
            
        }
    }
}
</script>

<style lang="scss" scoped>

    img {
        max-width: 40px;
    }

</style>