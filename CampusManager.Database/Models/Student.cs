using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace CampusManager.Database.Models
{
    public class Student
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

        public string Phone { get; set; }

        [Url]
        public string AvatarUrl { get; set; }

        public List<StudentCourse> StudentCourses { get; set; }
    }
}
