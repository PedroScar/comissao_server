const NotyfManager = (() => {
    let instance;

    const createInstance = () => {
        return new Notyf({
            duration: 5000,
            position: { x: "center", y: "top" },
            types: [
                {
                    type: "error",
                    ripple: false,
                    dismissible: true,
                    className: "px-4 rounded-md mb-2 bg-alert-pure text-alert-light",
                    icon: false,
                },
                {
                    type: "success",
                    ripple: false,
                    dismissible: true,
                    className: "px-4 rounded-md mb-2 bg-success-pure text-success-light",
                    icon: false,
                },
                {
                    type: "warning",
                    ripple: false,
                    dismissible: true,
                    className: "px-4 rounded-md mb-2 bg-warning-pure text-warning-light",
                    icon: false,
                },
            ],
        });
    };

    return {
        getInstance: () => {
            if (!instance) {
                instance = createInstance();
            }
            return instance;
        },
    };
})();