tailwind.config = {
  theme: {
    "colors":{"transparent":"#00000000","brand-pure":"#6BD9D5","brand-light":"#EDFCFD","brand-medium":"#B3F7FC","brand-dark":"#007F87","highlight-pure":"#C9F56A","highlight-light":"#F1FCD9","highlight-medium":"#87BF0D","highlight-dark":"#517308","low-pure":"#1F1F1F","low-light":"#808080","low-medium":"#4D4D4D","low-dark":"#141414","high-pure":"#FAFAFA","high-light":"#F0F2F2","high-medium":"#E0E0E0","high-dark":"#D1D1D1","alert-pure":"#FF3D00","alert-light":"#FBF1EF","alert-medium":"#FFC0AE","alert-dark":"#D80000","warning-pure":"#FFC107","warning-light":"#FDF4E3","warning-medium":"#FFE291","warning-dark":"#936800","success-pure":"#4CAF50","success-light":"#EFF5EF","success-medium":"#C1E2C0","success-dark":"#1F7827","color-01":"#00A3E0","color-02":"#DC143C","color-03":"#32CD32","color-04":"#FFD700","color-05":"#0759AB","color-06":"#A52A2A","color-07":"#008080","color-08":"#FF5722"},"borderRadius":{"none":"0","sm":"8px","md":"16px","lg":"24px","pill":"9999px"},
    fontFamily: {
      sans: ['Nunito', 'sans-serif'],
    },
    extend: {
      animation: {
        'slide-in': 'slideIn 0.3s ease-out',
        'slide-out': 'slideOut 0.3s ease-in',
        spin: 'spin 2s linear infinite',
      },
      keyframes: {
        slideIn: {
          '0%': { transform: 'translateY(100%)', opacity: '0' },
          '100%': { transform: 'translateY(0)', opacity: '1' },
        },
        slideOut: {
          '0%': { transform: 'translateY(0)', opacity: '1' },
          '100%': { transform: 'translateY(100%)', opacity: '0' },
        },
        spin: {
          '0%': { transform: 'rotate(0deg)' },
          '100%': { transform: 'rotate(360deg)' },
        },
      },
    },
    container: {
      center: true,
    }
  },
  plugins: [
    function({ addBase }) {
      addBase({
        'h1': { fontSize: '32px', fontWeight: '600', color: '#141414' },
        'h2': { fontSize: '26px', fontWeight: '600', color: '#141414' },
        'h3': { fontSize: '22px', fontWeight: '600', color: '#141414' },
        'h4': { fontSize: '20px', fontWeight: '600', color: '#141414' },
        'h5': { fontSize: '18px', fontWeight: '600', color: '#141414' },
        'h6': { fontSize: '16px', fontWeight: '600', color: '#141414' },
      });
    }
  ],
};