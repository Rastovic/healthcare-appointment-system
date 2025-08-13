// patientProfile.js

document.addEventListener('DOMContentLoaded', () => {
    const patientProfileForm = document.getElementById('patientProfileForm');

    if (patientProfileForm) {
        patientProfileForm.addEventListener('submit', async (event) => {
            event.preventDefault();

            const formData = new FormData(patientProfileForm);
            const profileData = Object.fromEntries(formData.entries());

            // Get userId from URL parameter
            const urlParams = new URLSearchParams(window.location.search);
            const userId = urlParams.get('userId');

            if (!userId) {
                console.error('User ID not found in URL');
                // TODO: Handle error, maybe display a message to the user
                return;
            }

            profileData.userId = userId; // Add userId to the data object

            try {
                const response = await fetch('/api/patients/create-profile', { // Make sure this is the correct endpoint URL
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        // TODO: Add any necessary authentication headers (e.g., JWT token)
                    },
                    body: JSON.stringify(profileData),
                });

                if (response.ok) {
                    // Profile created successfully
                    console.log('Patient profile created!');
                    // TODO: Redirect to dashboard or success page
                    // window.location.href = '/patient/dashboard'; // Example redirection
                } else {
                    // Handle errors
                    const errorData = await response.json(); // Assuming backend sends JSON error response
                    console.error('Error creating patient profile:', response.status, errorData);
                    // TODO: Display error message to user
                }
            } catch (error) {
                console.error('Error during profile creation:', error);
                // TODO: Display a generic error message to the user
            }
        });
    }
});
