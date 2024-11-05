<script lang="ts">
import CreateAccount from "../../features/users/CreateAccount.svelte";
</script>

<style>
    .signup-grid {
        display: grid;
        grid-template-columns: 5fr 3fr;
        gap: 1rem;
    }
</style>

<section class="signup-grid p-8">
    <CreateAccount />
    <img src="/signupnaw.png" alt="A.I. wants you to sign up" />
</section>
