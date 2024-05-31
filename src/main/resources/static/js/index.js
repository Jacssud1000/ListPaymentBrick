document.addEventListener("DOMContentLoaded", function() {
    const mp = new MercadoPago("APP_USR-4f4253b6-023c-4eba-9323-bd055d36f1ce");

    const bricksBuilder = mp.bricks();

    const renderPaymentBrick = async (bricksBuilder) => {
        const settings = {
            initialization: {
                amount: 100,
                preferenceId: "<PREFERENCE_ID>",
            },
            customization: {
                paymentMethods: {
                    ticket: "all",
                    bankTransfer: "all",
                    creditCard: "all",
                    debitCard: "all",
                    mercadoPago: "all",
                },
            },
            callbacks: {
                onReady: () => {
                    /*
                     Callback chamado quando o Brick estiver pronto.
                     Aqui você pode ocultar loadings do seu site, por exemplo.
                    */
                },
                onSubmit: ({ selectedPaymentMethod, formData }) => {
                    // callback chamado ao clicar no botão de submissão dos dados
                    return new Promise((resolve, reject) => {
                        fetch("/process_payment", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                            },
                            body: JSON.stringify(formData),
                        })
                            .then((response) => response.json())
                            .then((response) => {
                                // receber o resultado do pagamento
                                resolve();
                            })
                            .catch((error) => {
                                reject();
                            });
                    });
                },
                onError: (error) => {
                    console.error(error);
                },
            },
        };
        window.paymentBrickController = await bricksBuilder.create(
            "payment",
            "paymentBrick_container",
            settings
        );
    };
    renderPaymentBrick(bricksBuilder);

});