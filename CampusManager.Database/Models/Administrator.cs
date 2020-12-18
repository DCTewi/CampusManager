using System.ComponentModel.DataAnnotations;

namespace CampusManager.Database.Models
{
    public class Administrator
    {
        public int Id { get; set; }

        [Required, EmailAddress]
        public string Email { get; set; }

        [Required, MaxLength(30)]
        public string UserName { get; set; }

        [Required]
        public string PasswordHash { get; set; }

        [Required]
        public string SecuritySalt { get; set; }
    }
}
